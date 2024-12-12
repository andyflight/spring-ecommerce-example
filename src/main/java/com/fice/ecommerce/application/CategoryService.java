package com.fice.ecommerce.application;

import com.fice.ecommerce.domain.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getByName(String categoryName);

    List<Category> getAllCategoriesByNames(List<String> names);

}
