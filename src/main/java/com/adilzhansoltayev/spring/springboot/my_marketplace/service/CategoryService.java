package com.adilzhansoltayev.spring.springboot.my_marketplace.service;

import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();

    Category getCategoryById(Long categoryId);
}
