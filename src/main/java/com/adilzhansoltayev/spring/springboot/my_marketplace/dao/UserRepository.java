package com.adilzhansoltayev.spring.springboot.my_marketplace.dao;

import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
