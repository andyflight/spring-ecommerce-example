package com.fice.ecommerce.presenter.mapper;

import com.fice.ecommerce.domain.Category;
import com.fice.ecommerce.presenter.dto.category.CategoryResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DtoCategoryMapper {

    CategoryResponseDto toDto(Category category);

    List<CategoryResponseDto> toDto(List<Category> categoryList);

}
