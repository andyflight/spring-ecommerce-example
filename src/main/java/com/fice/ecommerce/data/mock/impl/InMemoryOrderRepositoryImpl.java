package com.fice.ecommerce.data.mock.impl;

import com.fice.ecommerce.data.OrderRepository;
import com.fice.ecommerce.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryOrderRepositoryImpl implements OrderRepository {
    private final Map<String, Order> ordersMap;
    
    public InMemoryOrderRepositoryImpl() {
        ordersMap = new HashMap<>();
    }
    
    @Override
    public Order save(Order order) {
        ordersMap.put(order.getOrderNumber().toString(), order);
        return order;
    }
    
    @Override
    public Optional<Order> findByNumber(UUID orderNumber) {
        return Optional.ofNullable(ordersMap.get(orderNumber.toString()));
    }
    
    @Override
    public List<Order> findByCustomerReference(UUID customerReference) {
        return ordersMap.values().stream()
            .filter(order -> order.getCustomer().getCustomerReference().equals(customerReference))
            .toList();
    }
    
    @Override
    public void deleteByNumber(UUID orderNumber) {
        ordersMap.remove(orderNumber.toString());
    }
}
