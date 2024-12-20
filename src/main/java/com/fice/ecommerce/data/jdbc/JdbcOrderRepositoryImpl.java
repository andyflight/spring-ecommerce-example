package com.fice.ecommerce.data.jdbc;

import com.fice.ecommerce.application.exceptions.CustomerNotFoundException;
import com.fice.ecommerce.application.exceptions.ProductNotFoundException;
import com.fice.ecommerce.data.OrderRepository;
import com.fice.ecommerce.data.jdbc.extractors.OrderRowExtractor;
import com.fice.ecommerce.data.jdbc.queries.OrderSQLQuery;
import com.fice.ecommerce.domain.Order;
import com.fice.ecommerce.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JdbcOrderRepositoryImpl implements OrderRepository {
    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;
    private final OrderSQLQuery orderSQLQuery;
    private final OrderRowExtractor orderRowMapper;
    
    @Override
    public Optional<Order> findByNumber(UUID orderNumber) {
        List<Order> order = jdbcTemplate.query(
            orderSQLQuery.findOneByNumber(),
            orderRowMapper,
            orderNumber
        );
        
        return Optional.ofNullable(order).isEmpty() ?
            Optional.empty() :
            Optional.of(order.getFirst());
    }
    
    @Override
    public List<Order> findByCustomerReference(UUID customerReference) {
        return jdbcTemplate.query(
            orderSQLQuery.findManyByCustomerReference(),
            orderRowMapper,
            customerReference
        );
    }
    
    @Override
    public Order save(Order order) {
        return transactionTemplate.execute(status -> {
            Order currentOrder = order;
            
            if (isExists(order.getId())) {
                jdbcTemplate.update(
                    orderSQLQuery.updateOrderById(),
                    order.getStatus().toString(),
                    order.getId()
                );
            } else {
                currentOrder = createOrder(order);
            }
            
            if (order.getOrderItems().isEmpty()) {
                return order;
            }
            
            updateOrderItems(currentOrder);
            return currentOrder;
        });
    }
    
    @Override
    public void deleteByNumber(UUID orderNumber) {
        Long orderId = jdbcTemplate.queryForObject(
            orderSQLQuery.getIdByOrderNumber(),
            Long.class,
            orderNumber
        );
        
        transactionTemplate.execute(action -> {
            jdbcTemplate.update(
                orderSQLQuery.deleteOrderItemById(),
                orderId
            );
            jdbcTemplate.update(
                orderSQLQuery.deleteOrderById(),
                orderId
            );
            return null;
        });
    }
    
    private Order createOrder(Order order) {
        Long customerId;
        try {
            customerId = jdbcTemplate.queryForObject(
                "SELECT id FROM customer WHERE customer_reference = ?",
                Long.class,
                order.getCustomer().getCustomerReference()
            );
        } catch (EmptyResultDataAccessException e) {
            throw new CustomerNotFoundException(order.getCustomer().getCustomerReference());
        }
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(
            con -> {
                PreparedStatement ps = con.prepareStatement(orderSQLQuery.createOrder(), Statement.RETURN_GENERATED_KEYS);
                ps.setObject(1, order.getOrderNumber());
                ps.setString(2, order.getStatus().toString());
                ps.setLong(3, customerId);
                return ps;
            },
            keyHolder
        );
        
        return order.toBuilder()
            .customer(
                order.getCustomer().toBuilder()
                    .id(customerId)
                    .build()
            )
            .id(keyHolder.getKey().longValue())
            .build();
    }
    
    private void updateOrderItems(Order order) {
        jdbcTemplate.update(
            orderSQLQuery.deleteOrderItemById(),
            order.getId()
        );
        
        for (OrderItem orderItem : order.getOrderItems()) {
            Long productId;
            try {
                productId = jdbcTemplate.queryForObject(
                    "SELECT id FROM product WHERE code = ?",
                    Long.class,
                    orderItem.getProduct().getCode()
                );
            } catch (EmptyResultDataAccessException e) {
                throw new ProductNotFoundException(orderItem.getProduct().getCode());
            }
            
            jdbcTemplate.update(
                orderSQLQuery.createOrderItem(),
                order.getId(),
                productId,
                orderItem.getQuantity(),
                orderItem.getProductOldPrice()
            );
        }
    }
    
    private Boolean isExists(Long id) {
        return jdbcTemplate.queryForObject(
            orderSQLQuery.isExistsById(),
            Boolean.class,
            id
        );
    }
}
