package com.adilzhansoltayev.spring.springboot.my_marketplace.service;

import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Good;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface GoodService {

    public List<Good> getAllGoods();

    public void saveGood(Principal principal, Good good, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException;

    public void updateQuantity(int id, long newQuantity);

    public Good getGoods(int id);

    public void deleteGood(User user, int id);

    public Good getGoodById(int id);

    public List<Good> findAllByName(String name);

//    public List<Good> findAllByNameAndCity(String name, String city);

//    public List<Good> findAllByNameAndCityAndCategory(String name, String city, Long categoryId);

    public List<Good> findAllByNameAndCityAndCategoryAndPriceRange(String name, String city, Long categoryId, Long minPrice, Long maxPrice);

    public List<Good> findAllWithSorting(String name, String city, Long categoryId, Long minPrice, Long maxPrice, String sortField, String sortOrder);

    }
