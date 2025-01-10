package com.adilzhansoltayev.spring.springboot.my_marketplace.controller;

import com.adilzhansoltayev.spring.springboot.my_marketplace.dao.UserRepository;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.User;
import com.adilzhansoltayev.spring.springboot.my_marketplace.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с такой почтой " + user.getEmail() +
                    " уже существует");
            return "registration";
        }
//        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String securityUrl(){
        return "hello";
    }
}
