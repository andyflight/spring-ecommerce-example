package com.fice.ecommerce.presenter.mapper;

import com.fice.ecommerce.application.context.order.CreateOrderContext;
import com.fice.ecommerce.domain.Customer;
import com.fice.ecommerce.domain.Order;
import com.fice.ecommerce.presenter.dto.order.CreateOrderRequestDto;
import com.fice.ecommerce.presenter.dto.order.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = { DtoOrderItemMapper.class })
public interface DtoOrderMapper {
    CreateOrderContext toCreateContext(CreateOrderRequestDto order);
    
    @Mapping(source = "customer", target = "customerId", qualifiedByName = "mapCustomerToId")
    OrderResponseDto toDto(Order order);
    
    @Mapping(source = "customer", target = "customerId", qualifiedByName = "mapCustomerToId")
    List<OrderResponseDto> toDto(List<Order> orders);
    
    @Named("mapCustomerToId")
    default UUID mapCustomerToId(Customer customer) {
        if (customer == null) {
            return null;
        }
        return customer.getCustomerReference();
    }
}
