package com.fice.ecommerce.application.exceptions;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException {
  public CustomerNotFoundException(UUID reference) {
    super(String.format("Customer with reference %s not found.", reference));
  }
}
