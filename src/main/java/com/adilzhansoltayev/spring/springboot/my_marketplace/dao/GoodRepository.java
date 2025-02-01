package com.adilzhansoltayev.spring.springboot.my_marketplace.dao;

import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodRepository extends JpaRepository<Good, Long>{
    Good findById(long id);

    @Query("select g from Good g where lower(g.name) like lower(concat('%', :name, '%'))")
    List<Good> findByNameContaining(String name);

    List<Good> findByCategory(String category);

    List<Good> findByPriceBetween(double priceMin, double priceMax);
}
