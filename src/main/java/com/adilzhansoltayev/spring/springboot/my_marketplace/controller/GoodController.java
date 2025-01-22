package com.adilzhansoltayev.spring.springboot.my_marketplace.controller;

import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Category;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Good;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.User;
import com.adilzhansoltayev.spring.springboot.my_marketplace.service.impl.CategoryServiceImpl;
import com.adilzhansoltayev.spring.springboot.my_marketplace.service.impl.GoodServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@Transactional
public class GoodController {
    private final GoodServiceImpl goodService;
    private final CategoryServiceImpl categoryService;

    @GetMapping("/")
    public String goods(@RequestParam(name = "searchWord", required = false) String name,
                        @RequestParam(name = "searchCity", required = false) String city,
                        @RequestParam(name = "searchCategory", required = false) Long categoryId,
                        @RequestParam(name = "minPrice", required = false) Long minPrice,
                        @RequestParam(name = "maxPrice", required = false) Long maxPrice,
                        @RequestParam(name = "sortField", required = false) String sortField,
                        @RequestParam(name = "sortOrder", required = false) String sortOrder,
                        Principal principal, Model model) {

        log.info("Параметры запроса: searchWord={}, searchCity={}, searchCategory={}, minPrice={}, maxPrice={}, sortField={}, sortOrder={}",
                name, city, categoryId, minPrice, maxPrice, sortField, sortOrder);

        List<Good> goods = goodService.findAllWithSorting(name, city, categoryId, minPrice, maxPrice, sortField, sortOrder);
        List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("goods", goods);
        model.addAttribute("categories", categories);
//        model.addAttribute("goods", goodService.findAllByName(name));
        model.addAttribute("user", goodService.getUserByPrincipal(principal));
        model.addAttribute("searchWord", name);
        model.addAttribute("searchCity", city);
        model.addAttribute("searchCategory", categoryId);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortOrder", sortOrder);

        log.info("Найдено товаров: {}", goods.size());

        return "goods";
    }

    @GetMapping("/goods")
    public List<Good> showAllGoods() {
        return goodService.getAllGoods();
    }

    @GetMapping("/goods/{id}")

    public String goodInfo(@PathVariable int id, Model model, Principal principal) {
        Good good = goodService.getGoods(id);
        model.addAttribute("user", goodService.getUserByPrincipal(principal));
        model.addAttribute("goods", good);
        model.addAttribute("images", good.getImages());
        model.addAttribute("authorGood", good.getUser());
        return "goods-info";
    }

    @PostMapping("/goods/create")
    public String addNewGood(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,
                             @RequestParam("file3") MultipartFile file3,
                             @RequestParam("categoryId") Long categoryId,
                             Good good, Principal principal) throws IOException {
        Category category = categoryService.getCategoryById(categoryId);
        good.setCategory(category);

        goodService.saveGood(principal, good, file1, file2, file3);
        log.info("Добавление товара: {}", good);
        log.info("Пользователь: {}", goodService.getUserByPrincipal(principal));
        return "redirect:/my/goods";
    }

    @PostMapping("/goods/update/{id}")
    public String updateGoodQuantity(@PathVariable int id,
                                     @RequestParam(required = false) long quantity, RedirectAttributes redirectAttributes) {
        goodService.updateQuantity(id, quantity);
        return "redirect:/warehouse";
    }

    @PostMapping("/goods/delete/{id}")
    public String deleteGood(@PathVariable int id, Principal principal) {
        goodService.deleteGood(goodService.getUserByPrincipal(principal), id);
        return "redirect:/my/goods";
    }

    @GetMapping("/my/goods")
    public String userGoods(Principal principal, Model model) {
        User user = goodService.getUserByPrincipal(principal);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("user", user);
        model.addAttribute("goods", user.getGoods());
        model.addAttribute("categories", categories);
        return "my-goods";
    }

    @GetMapping("/goods/name/{name}")
    public List<Good> showAllGoodsByName(@PathVariable String name) {
        List<Good> goods = goodService.findAllByName(name);
        return goods;
    }

    @GetMapping("/warehouse")
    public String warehouse(Model model, Principal principal) {
        model.addAttribute("user", goodService.getUserByPrincipal(principal));
        model.addAttribute("goods", goodService.getAllGoods());
        return "warehouse";
    }

}
