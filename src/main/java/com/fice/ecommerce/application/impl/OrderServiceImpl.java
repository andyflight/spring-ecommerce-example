package com.fice.ecommerce.application.impl;

import com.fice.ecommerce.application.OrderService;
import com.fice.ecommerce.application.context.order.CreateOrderContext;
import com.fice.ecommerce.application.context.order.OrderItemContext;
import com.fice.ecommerce.application.exceptions.OrderNotFoundException;
import com.fice.ecommerce.common.OrderStatus;
import com.fice.ecommerce.data.OrderRepository;
import com.fice.ecommerce.domain.Customer;
import com.fice.ecommerce.domain.Order;
import com.fice.ecommerce.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
  
    @Override
    public Order createOrder(CreateOrderContext context) {
        List<OrderItem> orderItems = context.getOrderItems().stream()
            .map(OrderItemContext::toOrderItem).toList();
        
        Order order = Order.builder()
            .orderNumber(UUID.randomUUID())
            .customer(Customer.builder()
                .customerReference(context.getCustomerReference())
                .build()
            )
            .orderItems(orderItems)
            .status(context.getOrderStatus())
            .createdAt(new Date())
            .build();
        
        return orderRepository.save(order);
    }
    
    @Override
    public Order updateOrderStatus(UUID orderNumber, OrderStatus orderStatus) {
        Order oldOrder = orderRepository.findByNumber(orderNumber)
            .orElseThrow(() -> new OrderNotFoundException(orderNumber.toString()));
        
        Order newOrder = oldOrder.toBuilder()
            .status(orderStatus)
            .build();
        
        orderRepository.save(newOrder);
        
        return newOrder;
    }
    
    @Override
    public Order getOrderByNumber(UUID orderNumber) {
        return orderRepository.findByNumber(orderNumber)
            .orElseThrow(() -> new OrderNotFoundException(orderNumber.toString()));
    }
    
    @Override
    public List<Order> getCustomerOrders(UUID customerReference) {
        return orderRepository.findByCustomerReference(customerReference);
    }
    
    @Override
    public void deleteOrder(UUID orderNumber) {
        orderRepository.deleteByNumber(orderNumber);
    }
}
