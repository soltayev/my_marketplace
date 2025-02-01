package com.adilzhansoltayev.spring.springboot.my_marketplace.service;

import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Good;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface GoodService {

    void saveOrUpdateGood(Good good) throws IOException;

    void deleteGood(long id);

    Good getGoodById(long id);

    List<Good> getGoodByName(String name);

    List<Good> getGoodByCategory(String category);

    List<Good> getGoodByPrice(double priceMin, double priceMax);

    Page<Good> getPageGoods(Pageable pageable);

    void updateQuantity(long id, long quantity);

}
