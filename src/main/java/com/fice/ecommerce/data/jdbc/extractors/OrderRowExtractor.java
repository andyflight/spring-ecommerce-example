package com.fice.ecommerce.data.jdbc.extractors;

import com.fice.ecommerce.common.OrderStatus;
import com.fice.ecommerce.domain.Customer;
import com.fice.ecommerce.domain.Order;
import com.fice.ecommerce.domain.OrderItem;
import com.fice.ecommerce.domain.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class OrderRowExtractor implements ResultSetExtractor<List<Order>> {
    
    @Override
    public List<Order> extractData(ResultSet rs) throws SQLException, DataAccessException {
        if (!rs.isBeforeFirst()) {
            return List.of();
        }
        
        Map<Long, Order> map = new HashMap<>();
        
        while (rs.next()) {
            Long id = rs.getLong("id");
            
            Order order = map.computeIfAbsent(id, (orderId) -> {
                try {
                    return Order.builder()
                        .id(id)
                        .orderNumber(rs.getObject("order_number", UUID.class))
                        .createdAt(rs.getTimestamp("created_at"))
                        .status(OrderStatus.valueOf(rs.getString("status")))
                        .customer(
                            Customer.builder()
                                .customerReference(rs.getObject("customer_reference", UUID.class))
                                .build()
                        )
                        .orderItems(new ArrayList<>())
                        .build();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            
            if (rs.getObject("item_id") != null) {
                order.getOrderItems().add(
                    OrderItem.builder()
                        .id(rs.getLong("item_id"))
                        .productOldPrice(rs.getDouble("product_old_price"))
                        .quantity(rs.getInt("item_quantity"))
                        .product(
                            Product.builder()
                                .id(rs.getLong("product_id"))
                                .code(rs.getString("product_code"))
                                .build()
                        ).build()
                );
            }
        }
        
        return new ArrayList<>(map.values());
    }
}
