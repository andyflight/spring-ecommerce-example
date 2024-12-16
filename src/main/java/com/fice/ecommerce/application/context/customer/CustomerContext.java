package com.fice.ecommerce.application.context.customer;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class CustomerContext {
  UUID customerReference;
  String fullName;
  String username;
  String password;
  String email;
}
