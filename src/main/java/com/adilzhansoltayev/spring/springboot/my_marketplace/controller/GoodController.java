package com.adilzhansoltayev.spring.springboot.my_marketplace.controller;


import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Good;
import com.adilzhansoltayev.spring.springboot.my_marketplace.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @GetMapping("/goods")
    public List<Good> showAllGoods() {
        List<Good> allGoods = goodService.getAllGoods();

        return allGoods;
    }

    @GetMapping("/goods/{id}")
    public Good getGood(@PathVariable int id) {
        Good good = goodService.getGood(id);

        return good;
    }

    @PostMapping("/goods")
    public Good addNewGood(@RequestBody Good good){
        goodService.saveGood(good);
        return good;
    }

    @PutMapping("/goods")
    public Good updateGood(@RequestBody Good good){
        goodService.saveGood(good);
        return good;
    }

    @DeleteMapping("/goods/{id}")
    public String deleteGood(@PathVariable int id) {
        Good good = goodService.getGood(id);
        goodService.deleteGood(id);
        return "Good with id = " + id + " was deleted";
    }

    @GetMapping("/goods/name/{name}")
    public List<Good> showAllGoodsByName(@PathVariable String name) {
        List<Good> goods = goodService.findAllByName(name);
        return goods;
    }

}
