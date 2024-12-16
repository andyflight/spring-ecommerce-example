package com.fice.ecommerce.data;

import com.fice.ecommerce.domain.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {

  Customer save(Customer customer);

  Optional<Customer> findByReference(UUID reference);

  void deleteByReference(UUID reference);
}
