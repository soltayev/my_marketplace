package com.adilzhansoltayev.spring.springboot.my_marketplace.controller;

import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Good;
import com.adilzhansoltayev.spring.springboot.my_marketplace.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @GetMapping("/goods")
    public Page<Good> getAllGoods(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(defaultValue = "id") String sortBy,
                                  @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return goodService.getPageGoods(pageable);
    }

    @GetMapping("/goods/{id}")
    public Good goodInfo(@PathVariable long id) {
        return goodService.getGoodById(id);
    }

    @GetMapping("/goods/name/{name}")
    public List<Good> getGoodByName(@PathVariable("name") String name) {
        return goodService.getGoodByName(name);
    }

    @GetMapping("/goods/category/{category}")
    public List<Good> getGoodByCategory(@PathVariable("category") String category) {
        return goodService.getGoodByCategory(category);
    }

    @GetMapping("/goods/price/{priceMin}/{priceMax}")
    public List<Good> getGoodByPriceRange(@PathVariable("priceMin") Long priceMin,
                                          @PathVariable("priceMax") Long priceMax) {
        return goodService.getGoodByPrice(priceMin, priceMax);
    }

    @PostMapping("/goods/create")
    public Good addNewGood(@RequestBody Good good) throws IOException {

        goodService.saveOrUpdateGood(good);
        return good;
    }

    @PostMapping("/goods/update")
    public Good updateGood(@RequestBody Good good) throws IOException {
        goodService.saveOrUpdateGood(good);
        return goodService.getGoodById(good.getId());
    }

    @PostMapping("/goods/update_quantity/{id}")
    public Good updateQuantity(@PathVariable long id,
                               @RequestParam(value = "quantity") long quantity){
        goodService.updateQuantity(id, quantity);
        return goodService.getGoodById(id);
    }

    @DeleteMapping("/goods/delete/{id}")
    public String deleteGood(@PathVariable long id) {
        goodService.deleteGood(id);
        return "good with id " + id + " deleted";
    }
}
