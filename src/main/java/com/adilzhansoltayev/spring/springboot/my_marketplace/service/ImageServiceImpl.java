package com.adilzhansoltayev.spring.springboot.my_marketplace.service;

import com.adilzhansoltayev.spring.springboot.my_marketplace.dao.ImageRepository;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;


    @Override
    public Images getImage(int id) {
        Images image = imageRepository.findById(id).orElse(null);
        return image;
    }
}
