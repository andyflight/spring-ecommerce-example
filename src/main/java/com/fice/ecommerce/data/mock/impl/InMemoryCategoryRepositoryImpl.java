package com.fice.ecommerce.data.mock.impl;


import com.fice.ecommerce.domain.Category;
import com.fice.ecommerce.data.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class InMemoryCategoryRepositoryImpl implements CategoryRepository {

    private final Map<Long, Category> categoryMap;

    public InMemoryCategoryRepositoryImpl() {
        this.categoryMap = Map.of(
                1L, Category.builder().name("Food").build(),
                2L, Category.builder().name("Furniture").build(),
                3L, Category.builder().name("Toys").build(),
                4L, Category.builder().name("Accessories").build(),
                5L, Category.builder().name("Health").build()
        );
    }

    @Override
    public List<Category> findAll() {
        return categoryMap.values().stream().toList();
    }

    @Override
    public List<Category> findAllByName(List<String> names) {
        if (names == null || names.isEmpty()) {
            return List.of();
        }
        return categoryMap.values().stream()
                .filter(category -> names.contains(category.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findByName(String name) {
        return categoryMap.values().stream()
                .filter(category -> category.getName().equalsIgnoreCase(name))
                .findFirst();
    }

}
