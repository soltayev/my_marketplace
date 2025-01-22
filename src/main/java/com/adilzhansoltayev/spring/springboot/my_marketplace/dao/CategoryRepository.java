package com.adilzhansoltayev.spring.springboot.my_marketplace.dao;

import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAll();
}
