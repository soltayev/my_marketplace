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

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "price")
    private long price;

    @Column(name = "info", columnDefinition = "text")
    private String info;

    @Column(name = "city")
    private String city;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "good", orphanRemoval = true)
    private List<Images> images = new ArrayList<>();

    private int previewImageId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "quantity")
    private Long quantity;

    @PrePersist
    private void init() {
        createdAt = LocalDateTime.now();
    }

    public void addImageToGood(Images image) {
        image.setGood(this);
        images.add(image);
    }

}
