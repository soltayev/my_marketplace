package com.adilzhansoltayev.spring.springboot.my_marketplace.service;

import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Good;

import java.util.List;

public interface GoodService {

    public List<Good> getAllGoods();

    public void saveGood(Good good);

    public Good getGood(int id);

    public void deleteGood(int id);

    public List<Good> findAllByName(String name);
}
