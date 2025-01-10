package com.adilzhansoltayev.spring.springboot.my_marketplace.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "goods")
@Data
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private int price;

    @Column(name = "info", columnDefinition = "text")
    private String info;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "good")
    private List<Images> images = new ArrayList<>();

//    @Column(name = "previewImageId")
    private int previewImageId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @PrePersist
    private void init() {
        createdAt = LocalDateTime.now();
    }

    public void addImageToProduct(Images image) {
        image.setGood(this);
        images.add(image);
    }

}
