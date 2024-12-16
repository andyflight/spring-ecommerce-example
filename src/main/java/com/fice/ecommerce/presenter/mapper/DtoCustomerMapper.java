package com.fice.ecommerce.presenter.mapper;

import com.fice.ecommerce.application.context.customer.CustomerContext;
import com.fice.ecommerce.domain.Customer;
import com.fice.ecommerce.presenter.dto.customer.CustomerRequestDto;
import com.fice.ecommerce.presenter.dto.customer.CustomerResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DtoCustomerMapper {

  @Mapping(target="customerReference", ignore = true)
  CustomerContext toContext(CustomerRequestDto dto);

  CustomerResponseDto toDto(Customer customer);
}
