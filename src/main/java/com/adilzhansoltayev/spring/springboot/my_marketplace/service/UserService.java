package com.adilzhansoltayev.spring.springboot.my_marketplace.service;

import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.User;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface UserService {
    public boolean createUser(User user);
    public List<User> list();
    public void banUser(Long id);
    public void changeUserRoles(User user, Map<String, String> form);
    public User getUserByPrincipal(Principal principal);
}
