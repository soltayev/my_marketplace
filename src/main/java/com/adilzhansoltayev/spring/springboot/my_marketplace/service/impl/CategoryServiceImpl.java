package com.adilzhansoltayev.spring.springboot.my_marketplace.service.impl;

import com.adilzhansoltayev.spring.springboot.my_marketplace.dao.CategoryRepository;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Category;
import com.adilzhansoltayev.spring.springboot.my_marketplace.service.CategoryService;
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
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }
}
