package com.fice.ecommerce.application;

import com.fice.ecommerce.application.context.order.CreateOrderContext;
import com.fice.ecommerce.common.OrderStatus;
import com.fice.ecommerce.domain.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
  Order createOrder(CreateOrderContext context);
  Order getOrderByNumber(UUID orderNumber);
  List<Order> getCustomerOrders(UUID customerReference);
  Order updateOrderStatus(UUID orderNumber, OrderStatus orderStatus);
  void deleteOrder(UUID orderNumber);
}
