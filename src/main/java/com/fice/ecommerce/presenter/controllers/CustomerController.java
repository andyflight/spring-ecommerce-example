package com.fice.ecommerce.presenter.controllers;

import com.fice.ecommerce.application.CustomerService;
import com.fice.ecommerce.domain.Customer;
import com.fice.ecommerce.presenter.dto.SimpleResponse;
import com.fice.ecommerce.presenter.dto.customer.CustomerRequestDto;
import com.fice.ecommerce.presenter.dto.customer.CustomerResponseDto;
import com.fice.ecommerce.presenter.mapper.DtoCustomerMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

  private final CustomerService customerService;
  private final DtoCustomerMapper dtoCustomerMapper;

  @PostMapping
  public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody @Valid CustomerRequestDto request) {
    Customer customer = customerService.createCustomer(dtoCustomerMapper.toContext(request));
    return ResponseEntity.ok(dtoCustomerMapper.toDto(customer));
  }

  @PutMapping("/{reference}")
  public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable UUID reference,
                                                            @RequestBody @Valid CustomerRequestDto request) {
    Customer customer = customerService.updateCustomer(reference, dtoCustomerMapper.toContext(request));
    return ResponseEntity.ok(dtoCustomerMapper.toDto(customer));
  }

  @GetMapping("/{reference}")
  public ResponseEntity<CustomerResponseDto> getCustomerByReference(@PathVariable UUID reference) {
    Customer customer = customerService.getByReference(reference);
    return ResponseEntity.ok(dtoCustomerMapper.toDto(customer));
  }

  @DeleteMapping("/{reference}")
  public ResponseEntity<SimpleResponse> deleteCustomer(@PathVariable UUID reference) {
    customerService.deleteCustomer(reference);
    return ResponseEntity.ok(new SimpleResponse("Successful"));
  }
}

