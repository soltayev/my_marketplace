package com.adilzhansoltayev.spring.springboot.my_marketplace.controller;


import com.adilzhansoltayev.spring.springboot.my_marketplace.dao.GoodRepository;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Good;
import com.adilzhansoltayev.spring.springboot.my_marketplace.service.GoodService;
import com.adilzhansoltayev.spring.springboot.my_marketplace.service.GoodServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class GoodController {
    private final GoodServiceImpl goodService;

    @GetMapping("/")
    public String goods(@RequestParam(name = "name", required = false) String name, Principal principal, Model model) {
        model.addAttribute("goods", goodService.findAllByName(name));
        model.addAttribute("user", goodService.getUserByPrincipal(principal));
        return "goods";
    }

    @GetMapping("/goods")
    public List<Good> showAllGoods() {
        List<Good> allGoods = goodService.getAllGoods();

        return allGoods;
    }

    @GetMapping("/goods/{id}")
    public String goodInfo(@PathVariable int id, Model model) {
        Good good = goodService.getGoods(id);
        model.addAttribute("goods", good);
        model.addAttribute("images", good.getImages());
        return "goods-info";
    }

    @PostMapping("/goods/create")
    public String addNewGood(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                             @RequestParam("file3") MultipartFile file3, Good good, Principal principal) throws IOException {
        goodService.saveGood(principal, good, file1, file2, file3);
        return "redirect:/";
    }

//    @PutMapping("/goods")
//    public String updateGood(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
//                           @RequestParam("file3") MultipartFile file3, Good good) throws IOException {
//        goodService.saveGood(good, file1, file2, file3);
//        return "redirect:/";
//    }

    @PostMapping("/goods/delete/{id}")
    public String deleteGood(@PathVariable int id) {
        Good good = goodService.getGoods(id);
        goodService.deleteGood(id);
        return "redirect:/";
    }

    @GetMapping("/goods/name/{name}")
    public List<Good> showAllGoodsByName(@PathVariable String name) {
        List<Good> goods = goodService.findAllByName(name);
        return goods;
    }

}
