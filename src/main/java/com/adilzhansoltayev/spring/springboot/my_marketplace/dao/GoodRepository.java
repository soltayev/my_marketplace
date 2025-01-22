package com.adilzhansoltayev.spring.springboot.my_marketplace.dao;

import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodRepository extends JpaRepository<Good, Integer>{
    public List<Good> findAllByName(String name);

    public List<Good> findByNameContainingIgnoreCase(String name);

    public List<Good> findByNameAndCityContainingIgnoreCaseAndCategoryId(String name, String city, Long categoryId);

    public List<Good> findByNameAndCityContainingIgnoreCase(String name, String city);

    public List<Good> findByCityContainingIgnoreCase(String city);

    public List<Good> findByCategoryId(Long categoryId);

    public List<Good> findByCityAndCategoryId(String city, Long categoryId);

    public List<Good> findByPriceBetweenAndNameContainingIgnoreCaseAndCityContainingIgnoreCaseAndCategoryId(
            long minPrice, long maxPrice, String name, String city, Long categoryId);

    public List<Good> findByPriceBetweenAndCityContainingIgnoreCaseAndCategoryId(
            long minPrice, long maxPrice, String city, Long categoryId);

    public List<Good> findByPriceBetweenAndNameContainingIgnoreCase(
            long minPrice, long maxPrice, String name);

    public List<Good> findByPriceBetween(Long minPrice, Long maxPrice);

    public List<Good> findByPriceGreaterThanEqual(Long minPrice);

    public List<Good> findByPriceLessThanEqual(Long maxPrice);
}
