package com.example.jejuddai.service;

import com.example.jejuddai.entity.Menu;
import com.example.jejuddai.entity.Review;
import com.example.jejuddai.entity.Store;
import com.example.jejuddai.repository.MenuRepository;
import com.example.jejuddai.repository.ReviewRepository;
import com.example.jejuddai.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    private static final double LATITUDE_VALUE = 1 / 109.958489129649955;
    private static final double LONGITUDE_VALUE = 1 / 88.74;

    public List<Store> getStoreByCategoryAndLocation(double lat, double lon, double range) {
//        double latStart = lat - range;
//        double latEnd = lat + range;
//        double lonStart = lon - range;
//        double lonEnd = lon + range;

        double latStart = lat - (LATITUDE_VALUE / 1000 * range);
        double latEnd = lat + (LATITUDE_VALUE / 1000 * range);
        double lonStart = lon - (LONGITUDE_VALUE / 1000 * range);
        double lonEnd = lon + (LONGITUDE_VALUE / 1000 * range);

        return storeRepository.findByLatitudeBetweenAndLongitudeBetween(latStart, latEnd, lonStart, lonEnd);
//        return storeRepository.findByLatitudeBetweenAndLongitudeBetweenAndIsNotGoodForChildFalseAndIsFoodSellingTrue(latStart, latEnd, lonStart, lonEnd);
    }

    public List<Menu> getMenuByStoreId(String storeId) {
        return menuRepository.findByStoreId(storeId);
    }

    public List<Review> getReviewByStoreId(String storeId) {
        return reviewRepository.findByStoreId(storeId);
    }

    public Store findById(String storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));
    }

}

