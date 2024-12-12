package com.fice.ecommerce.application.impl;

import com.fice.ecommerce.application.CategoryService;
import com.fice.ecommerce.application.exceptions.CategoryNotFoundException;
import com.fice.ecommerce.application.exceptions.CategoryPartialResultException;
import com.fice.ecommerce.domain.Category;
import com.fice.ecommerce.data.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name).orElseThrow(() -> new CategoryNotFoundException(name));
    }

    @Override
    public List<Category> getAllCategoriesByNames(List<String> names) {
        List<Category> categories = categoryRepository.findAllByName(names);

        List<String> notFoundCategories = names.stream()
                .filter(n -> !categories.stream().map(Category::getName).toList().contains(n))
                .toList();

        if (!notFoundCategories.isEmpty()) {
            throw new CategoryPartialResultException(notFoundCategories.toString());
        }

        return categories;
    }

}
