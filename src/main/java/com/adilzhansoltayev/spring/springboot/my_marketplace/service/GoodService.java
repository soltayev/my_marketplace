package com.adilzhansoltayev.spring.springboot.my_marketplace.service;

import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Good;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GoodService {

    public List<Good> getAllGoods();

    public void saveGood(Good good, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException;

    public Good getGoods(int id);

    public void deleteGood(int id);

    public List<Good> findAllByName(String name);
}
