package com.adilzhansoltayev.spring.springboot.my_marketplace.service;

import com.adilzhansoltayev.spring.springboot.my_marketplace.dao.UserRepository;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.User;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.enums.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(user.getEmail()) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Created user: {}", email);
        return true;
    }
}
