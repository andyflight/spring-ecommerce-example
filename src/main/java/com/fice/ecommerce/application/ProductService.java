package com.fice.ecommerce.application;

import com.fice.ecommerce.application.context.product.ProductContext;
import com.fice.ecommerce.domain.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(ProductContext context);

    Product updateProduct(String code, ProductContext context);

    Product getProductByCode(String code);

    List<Product> getProducts();

    List<Product> getProductsByCategory(String categoryName);

    void deleteProduct(String code);
}
