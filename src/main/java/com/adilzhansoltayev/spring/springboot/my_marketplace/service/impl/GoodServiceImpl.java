package com.adilzhansoltayev.spring.springboot.my_marketplace.service.impl;

import com.adilzhansoltayev.spring.springboot.my_marketplace.dao.GoodRepository;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Good;
import com.adilzhansoltayev.spring.springboot.my_marketplace.service.GoodService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoodServiceImpl implements GoodService {
    private final GoodRepository goodRepository;

    @Override
    @Transactional
    public void saveOrUpdateGood(Good good){
        goodRepository.save(good);
    }

    @Override
    public List<Good> getGoodByName(String name) {

        return goodRepository.findByNameContaining(name);
    }

    @Override
    public List<Good> getGoodByCategory(String category) {
        return goodRepository.findByCategory(category);
    }

    @Override
    public List<Good> getGoodByPrice(double priceMin, double priceMax) {
        return goodRepository.findByPriceBetween(priceMin, priceMax);
    }

    @Override
    public Page<Good> getPageGoods(Pageable pageable) {
        return goodRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void updateQuantity(long id, long quantity) {
        Good good = goodRepository.findById(id);
        good.setQuantity(quantity);
        goodRepository.save(good);
    }

    @Override
    @Transactional
    public void deleteGood(long id) {
        Good good = goodRepository.findById(id);
        if (good == null) {
            log.error("Товар с id = {} не найден", id);
            return;
        }
        goodRepository.deleteById(id);
    }

    @Override
    public Good getGoodById(long id) {
        return goodRepository.findById(id);
    }
}

