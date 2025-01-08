package com.adilzhansoltayev.spring.springboot.my_marketplace.service;

import com.adilzhansoltayev.spring.springboot.my_marketplace.dao.GoodRepository;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Good;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodRepository goodRepository;

    @Override
    public List<Good> getAllGoods() {
        return goodRepository.findAll();
    }

    @Override
    public void saveGood(Good good, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Images image1, image2, image3;
        if(file1.getSize() != 0)  {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            good.addImageToProduct(image1);
        }
        if(file2.getSize() != 0)  {
            image2 = toImageEntity(file2);
            good.addImageToProduct(image2);
        }
        if(file3.getSize() != 0)  {
            image3 = toImageEntity(file3);
            good.addImageToProduct(image3);
        }
        Good goodFromDB = goodRepository.save(good);
        goodFromDB.setPreviewImageId(goodFromDB.getImages().get(0).getId());
        goodRepository.save(good);
    }

    private Images toImageEntity(MultipartFile file) throws IOException {
        Images image = new Images();
        image.setName(file.getName());
        image.setOriginalFilename(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    @Override
    public Good getGoods(int id) {
        return goodRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteGood(int id) {
        goodRepository.deleteById(id);
    }

    @Override
    public List<Good> findAllByName(String name) {
        if (name != null) return goodRepository.findAllByName(name);
        return goodRepository.findAll();
    }
}
