package com.adilzhansoltayev.spring.springboot.my_marketplace.service.impl;

import com.adilzhansoltayev.spring.springboot.my_marketplace.dao.GoodRepository;
import com.adilzhansoltayev.spring.springboot.my_marketplace.dao.UserRepository;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Good;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.Images;
import com.adilzhansoltayev.spring.springboot.my_marketplace.entity.User;
import com.adilzhansoltayev.spring.springboot.my_marketplace.service.GoodService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Comparator;
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
        if (principal == null) {
            log.error("Principal is null, user cannot be authenticated.");
            throw new RuntimeException("Principal is null");
        }

        User user = getUserByPrincipal(principal);
        good.setUser(user);

        log.info("Авторизованный пользователь: {} добавляет товар: {}", user.getEmail(), good.getName());

        Images image1, image2, image3;
        if(file1.getSize() != 0)  {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            good.addImageToGood(image1);
        }
        if(file2.getSize() != 0)  {
            image2 = toImageEntity(file2);
            good.addImageToGood(image2);
        }
        if(file3.getSize() != 0)  {
            image3 = toImageEntity(file3);
            good.addImageToGood(image3);
        }

        log.info("Сохраняем товар с названием: {}", good.getName());
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

    @Transactional
    public void updateQuantity(int id, long newQuantity) {
        Good good = goodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Товар не найден!"));
        good.setQuantity(newQuantity);
        goodRepository.save(good);
    }

    @Override
    public Good getGoods(int id) {
        return goodRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteGood(User user, int id) {
        Good good = goodRepository.findById(id).orElse(null);
        if (good == null) {
            log.error("Товар с id = {} не найден", id);
            return;
        }
        log.info("Good's user id: {}, Current user id: {}", good.getUser().getId(), user.getId());
        if (good.getUser().getId().equals(user.getId())) {
            goodRepository.deleteById(id);
            log.info("Товар с id = {} был удален, {}", id, good.getName());
        } else {
            log.error("Пользователь {} не является владельцем товара с id = {}", user.getEmail(), id);
        }
    }

    public Good getGoodById(int id) {
        return goodRepository.findById(id).orElse(null);
    }

    @Override
    public List<Good> findAllByName(String name) {
        if (name != null) return goodRepository.findAllByName(name);
        return goodRepository.findAll();
    }

    @Override
    public List<Good> findAllByNameAndCityAndCategoryAndPriceRange(String name, String city, Long categoryId, Long minPrice, Long maxPrice) {
        if (name != null && city != null && !city.isEmpty() && !name.isEmpty() && categoryId != null && minPrice != null && maxPrice != null) {
            log.info("Фильтрация по имени, городу, категории и диапазону цен.");
            return goodRepository.findByPriceBetweenAndNameContainingIgnoreCaseAndCityContainingIgnoreCaseAndCategoryId(
                    minPrice, maxPrice, name, city, categoryId);
        }

        if (minPrice != null && maxPrice != null && city != null && !city.isEmpty() && categoryId != null) {
            log.info("Фильтрация по диапазону цен, городу и категории.");
            return goodRepository.findByPriceBetweenAndCityContainingIgnoreCaseAndCategoryId(
                    minPrice, maxPrice, city, categoryId);
        }

        if (minPrice != null && maxPrice != null && name != null && !name.isEmpty()) {
            log.info("Фильтрация по диапазону цен и имени.");
            return goodRepository.findByPriceBetweenAndNameContainingIgnoreCase(minPrice, maxPrice, name);
        }

        if (name != null && city != null && !city.isEmpty() && !name.isEmpty() && categoryId != null) {
            log.info("Фильтрация по имени и городу и по категории.");

            return goodRepository.findByNameAndCityContainingIgnoreCaseAndCategoryId(name, city, categoryId);}

        if (name != null && city != null && !city.isEmpty() && !name.isEmpty()) {
            log.info("Фильтрация по имени и городу.");

            return goodRepository.findByNameAndCityContainingIgnoreCase(name, city);
        }
        if (city != null && !city.isEmpty() && categoryId != null) {
            log.info("Фильтрация по категории и по городу.");
            return goodRepository.findByCityAndCategoryId(city, categoryId);
        }

        if (name != null && (city == null || city.isEmpty()) && categoryId == null && minPrice == null && maxPrice == null) {
            log.info("Фильтрация только по имени.");

            return goodRepository.findByNameContainingIgnoreCase(name);
        }

        if (categoryId != null) {
            log.info("Фильтрация только по категории.");

            return goodRepository.findByCategoryId(categoryId);
        }

        if (city != null && !city.isEmpty()) {
            log.info("Фильтрация только по городу.");

            return goodRepository.findByCityContainingIgnoreCase(city);
        }

        if (minPrice != null && maxPrice != null) {
            log.info("Фильтрация по диапазону цен.");
            return goodRepository.findByPriceBetween(minPrice, maxPrice);
        }

        if (minPrice != null) {
            log.info("Фильтрация по диапазону цен от");
            return goodRepository.findByPriceGreaterThanEqual(minPrice);
        }

        if (maxPrice != null) {
            log.info("Фильтрация по диапазону цен до");
            return goodRepository.findByPriceLessThanEqual(maxPrice);
        }

        log.info("Без фильтрации.");
        return goodRepository.findAll();
    }

    public List<Good> findAllWithSorting(String name, String city, Long categoryId, Long minPrice, Long maxPrice, String sortField, String sortOrder) {
        List<Good> goods = findAllByNameAndCityAndCategoryAndPriceRange(name, city, categoryId, minPrice, maxPrice);

        if (sortField != null && sortOrder != null) {
            switch (sortField) {
                case "name" -> {
                    if ("asc".equals(sortOrder)) {
                        goods.sort(Comparator.comparing(Good::getName));
                    } else {
                        goods.sort((g1, g2) -> g2.getName().compareTo(g1.getName()));
                    }
                }
                case "price" -> {
                    if ("asc".equals(sortOrder)) {
                        log.info("price +");
                        goods.sort(Comparator.comparing(Good::getPrice));
                    } else {
                        log.info("price -");
                        goods.sort((g1, g2) -> Long.compare(g2.getPrice(), g1.getPrice()));
                    }
                }
                case "city" -> {
                    if ("asc".equals(sortOrder)) {
                        goods.sort(Comparator.comparing(Good::getCity));
                    } else {
                        goods.sort((g1, g2) -> g2.getCity().compareTo(g1.getCity()));
                    }
                }
            }
        }
        return goods;
    }


}
