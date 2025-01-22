package com.adilzhansoltayev.spring.springboot.my_marketplace.service.impl;

import com.adilzhansoltayev.spring.springboot.my_marketplace.dao.ImageRepository;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Images;
import com.adilzhansoltayev.spring.springboot.my_marketplace.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;


    @Override
    public Images getImage(int id) {
        Images image = imageRepository.findById(id).orElse(null);
        return image;
    }
}
