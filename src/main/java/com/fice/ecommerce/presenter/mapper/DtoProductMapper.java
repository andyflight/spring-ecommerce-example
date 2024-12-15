package com.fice.ecommerce.presenter.mapper;

import com.fice.ecommerce.application.context.product.ProductContext;
import com.fice.ecommerce.domain.Product;
import com.fice.ecommerce.domain.Category;
import com.fice.ecommerce.presenter.dto.product.ProductRequestDto;
import com.fice.ecommerce.presenter.dto.product.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DtoProductMapper {

    ProductContext toContext(ProductRequestDto dto);

    @Mapping(target="categoryNames", source="categories")
    ProductResponseDto toDto(Product product);

    List<ProductResponseDto> toDto(List<Product> products);

    default List<String> mapCategoriesToNames(List<Category> categories) {
        if (categories == null) {
            return null;
        }
        return categories.stream()
                .map(Category::getName)
                .toList();
    }

}
