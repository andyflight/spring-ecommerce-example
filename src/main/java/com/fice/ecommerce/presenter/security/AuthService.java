package com.fice.ecommerce.presenter.security;

import com.fice.ecommerce.application.CustomerService;
import com.fice.ecommerce.application.context.customer.CustomerContext;
import com.fice.ecommerce.domain.Customer;
import com.fice.ecommerce.presenter.security.encoder.PasswordEncoder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final CustomerService customerService;
  private final PasswordEncoder passwordEncoder;

  public Customer signUp(@Valid CustomerContext customerContext) {
    String encodedPassword = passwordEncoder.encode(customerContext.getPassword());
    CustomerContext secureContext = customerContext.toBuilder()
            .password(encodedPassword)
            .build();
    return customerService.createCustomer(secureContext);
  }
}
