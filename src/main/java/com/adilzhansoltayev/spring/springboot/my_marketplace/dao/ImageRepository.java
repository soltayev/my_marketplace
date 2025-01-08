package com.adilzhansoltayev.spring.springboot.my_marketplace.dao;

import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<Images, Integer> {
}
