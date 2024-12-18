package com.fice.ecommerce.data.mock.impl;

import com.fice.ecommerce.domain.Product;
import com.fice.ecommerce.data.ProductRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Scope("prototype")
public class InMemoryProductRepositoryImpl implements ProductRepository {

    private final Map<String, Product> productMap;

    public InMemoryProductRepositoryImpl() {
        this.productMap = new HashMap<>();
    }

    @Override
    public Product save(Product product) {
        productMap.put(product.getCode(), product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return productMap.values().stream().toList();
    }

    @Override
    public List<Product> findByCategoryName(String name) {
        return productMap.values().stream()
                .filter(product -> product.getCategories().stream()
                        .anyMatch(category -> category.getName().equalsIgnoreCase(name)))
                .toList();
    }

    @Override
    public Optional<Product> findByCode(String code) {
        return Optional.ofNullable(productMap.get(code));
    }

    @Override
    public void deleteByCode(String code) {
        productMap.remove(code);
    }

}
