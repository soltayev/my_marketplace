package com.adilzhansoltayev.spring.springboot.my_marketplace.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name = "goods")
@Data
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private double price;

    @Column(name = "info", columnDefinition = "text")
    private String info;

    @Column(name = "city")
    private String city;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "quantity")
    private long quantity;

    @PrePersist
    private void init() {
        createdAt = LocalDateTime.now();
    }

}
