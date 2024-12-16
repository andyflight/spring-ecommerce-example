package com.fice.ecommerce.presenter.controllers;

import com.fice.ecommerce.application.CategoryService;
import com.fice.ecommerce.presenter.dto.category.CategoryResponseDto;
import com.fice.ecommerce.presenter.mapper.DtoCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final DtoCategoryMapper dtoCategoryMapper;


    @GetMapping("")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {

        List<CategoryResponseDto> response = dtoCategoryMapper.toDto(categoryService.getAllCategories());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable String name) {

        CategoryResponseDto response = dtoCategoryMapper.toDto(categoryService.getByName(name));

        return ResponseEntity.ok(response);
    }

}
