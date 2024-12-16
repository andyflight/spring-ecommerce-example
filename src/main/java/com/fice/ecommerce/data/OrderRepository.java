package com.fice.ecommerce.data;

import com.fice.ecommerce.domain.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
  Optional<Order> findByNumber(UUID orderNumber);
  List<Order> findByCustomerReference(UUID customerReference);
  Order save(Order order);
  void deleteByNumber(UUID orderNumber);
}
