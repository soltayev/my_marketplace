package com.adilzhansoltayev.spring.springboot.my_marketplace.service;

import com.adilzhansoltayev.spring.springboot.my_marketplace.dao.GoodRepository;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodRepository goodRepository;

    @Override
    public List<Good> getAllGoods() {
        return goodRepository.findAll();
    }

    @Override
    public void saveGood(Good good) {

        goodRepository.save(good);
    }

    @Override
    public Good getGood(int id) {
        Good good = null;
        Optional<Good> optional = goodRepository.findById(id);
        if (optional.isPresent()){
            good = optional.get();
        }
        return good;
    }

    @Override
    public void deleteGood(int id) {
        goodRepository.deleteById(id);
    }

    @Override
    public List<Good> findAllByName(String name) {
        List<Good> goods = goodRepository.findAllByName(name);
        return goods;
    }
}
