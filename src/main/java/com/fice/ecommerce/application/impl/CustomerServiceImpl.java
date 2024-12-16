package com.fice.ecommerce.application.impl;

import com.fice.ecommerce.application.CustomerService;
import com.fice.ecommerce.application.context.customer.CustomerContext;
import com.fice.ecommerce.application.exceptions.CustomerNotFoundException;
import com.fice.ecommerce.domain.Customer;
import com.fice.ecommerce.data.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;

  @Override
  public Customer createCustomer(CustomerContext context) {
    Customer customer = Customer.builder()
            .customerReference(UUID.randomUUID())
            .fullName(context.getFullName())
            .username(context.getUsername())
            .password(context.getPassword())
            .email(context.getEmail())
            .build();
    return customerRepository.save(customer);
  }

  @Override
  public Customer updateCustomer(UUID reference, CustomerContext context) {
    Customer existingCustomer = customerRepository.findByReference(reference)
            .orElseThrow(() -> new CustomerNotFoundException(reference));

    Customer updatedCustomer = existingCustomer.toBuilder()
            .fullName(context.getFullName())
            .username(context.getUsername())
            .password(context.getPassword())
            .email(context.getEmail())
            .build();

    return customerRepository.save(updatedCustomer);
  }

  @Override
  public Customer getByReference(UUID reference) {
    return customerRepository.findByReference(reference)
            .orElseThrow(() -> new CustomerNotFoundException(reference));
  }

  @Override
  public void deleteCustomer(UUID reference) {
    if (customerRepository.findByReference(reference).isEmpty()) {
      throw new CustomerNotFoundException(reference);
    }
    customerRepository.deleteByReference(reference);
  }
}