package com.fice.ecommerce.data.mock.impl;

import com.fice.ecommerce.data.CustomerRepository;
import com.fice.ecommerce.domain.Customer;
import org.springframework.stereotype.Repository;

import java.util.*;

public class InMemoryCustomerRepositoryImpl implements CustomerRepository {

  private final Map<UUID, Customer> customerMap;

  public InMemoryCustomerRepositoryImpl() {
    this.customerMap = new HashMap<>();
  }

  @Override
  public Customer save(Customer customer) {
    customerMap.put(customer.getCustomerReference(), customer);
    return customer;
  }

  @Override
  public Optional<Customer> findByReference(UUID reference) {
    return Optional.ofNullable(customerMap.get(reference));
  }

  @Override
  public void deleteByReference(UUID reference) {
    customerMap.remove(reference);
  }
}
