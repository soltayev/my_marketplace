package com.adilzhansoltayev.spring.springboot.my_marketplace.service;

import com.adilzhansoltayev.spring.springboot.my_marketplace.dao.GoodRepository;
import com.adilzhansoltayev.spring.springboot.my_marketplace.dao.UserRepository;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Good;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Images;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GoodServiceImpl implements GoodService {
    private final GoodRepository goodRepository;
    private final UserRepository userRepository;

    @Override
    public List<Good> getAllGoods() {
        return goodRepository.findAll();
    }

    @Override
    public void saveGood(Principal principal, Good good, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        good.setUser(getUserByPrincipal(principal));
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
        log.info("Сохраняем новый товар. Название: {}; Автор: {}", good.getName(), good.getUser().getEmail());
        Good goodFromDB = goodRepository.save(good);
        goodFromDB.setPreviewImageId(goodFromDB.getImages().get(0).getId());
        goodRepository.save(good);
    }

    public User getUserByPrincipal(Principal principal) {
        if(principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
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
