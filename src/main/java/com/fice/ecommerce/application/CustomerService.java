package com.fice.ecommerce.application;

import com.fice.ecommerce.application.context.customer.CustomerContext;
import com.fice.ecommerce.domain.Customer;

import java.util.UUID;

public interface CustomerService {

  Customer createCustomer(CustomerContext context);

  Customer updateCustomer(UUID reference, CustomerContext context);

  Customer getByReference(UUID reference);

  void deleteCustomer(UUID reference);
}
