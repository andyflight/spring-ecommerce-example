package com.fice.ecommerce.application.impl;

import com.fice.ecommerce.application.CategoryService;
import com.fice.ecommerce.application.ProductService;
import com.fice.ecommerce.application.context.product.ProductContext;
import com.fice.ecommerce.application.exceptions.ProductNotFoundException;
import com.fice.ecommerce.domain.Category;
import com.fice.ecommerce.domain.Product;
import com.fice.ecommerce.data.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Override
    public Product createProduct(ProductContext productContext) {


        List<Category> categories = categoryService.getAllCategoriesByNames(productContext.getCategoryNames());

        Product product = Product.builder()
                .code(productContext.getCode())
                .name(productContext.getName())
                .description(productContext.getDescription())
                .price(productContext.getPrice())
                .categories(categories)
                .build();

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(String code, ProductContext productContext) {

        Product oldProduct = productRepository.findByCode(code)
                .orElseThrow(() -> new ProductNotFoundException(code));

        List<Category> categories = categoryService.getAllCategoriesByNames(productContext.getCategoryNames());

        Product newProduct = oldProduct.toBuilder()
                .code(productContext.getCode())
                .name(productContext.getName())
                .description(productContext.getDescription())
                .price(productContext.getPrice())
                .categories(categories)
                .build();

        return productRepository.save(newProduct);
    }

    @Override
    public Product getProductByCode(String code) {
        return productRepository.findByCode(code).orElseThrow(() -> new ProductNotFoundException(code));
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        if (!categoryName.isBlank()) {
            return productRepository.findByCategoryName(categoryName);

        }
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(String code) {
        productRepository.deleteByCode(code);
    }


}
