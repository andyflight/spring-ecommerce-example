package com.fice.ecommerce.presenter.mapper;

import com.fice.ecommerce.application.context.order.OrderItemContext;
import com.fice.ecommerce.domain.OrderItem;
import com.fice.ecommerce.domain.Product;
import com.fice.ecommerce.presenter.dto.orderItem.OrderItemRequestDto;
import com.fice.ecommerce.presenter.dto.orderItem.OrderItemResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface DtoOrderItemMapper {
    OrderItemContext toContext(OrderItemRequestDto orderItem);
    
    @Mapping(source = "product", target = "productCode", qualifiedByName = "mapProductToCode")
    OrderItemResponseDto toDto(OrderItem orderItem);
    
    @Named("mapProductToCode")
    default String mapProductToCode(Product product) {
        if (product == null) {
            return null;
        }
        return product.getCode();
    }
}
