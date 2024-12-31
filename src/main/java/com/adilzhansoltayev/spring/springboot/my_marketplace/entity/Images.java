package com.adilzhansoltayev.spring.springboot.my_marketplace.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "goods_photos")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "product_id")
    private int productId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Good good;
}
