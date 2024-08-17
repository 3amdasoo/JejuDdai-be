package com.example.jejuddai.repository;

import com.example.jejuddai.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, String> {
//    List<Store> findByCategoryAndLatitudeBetweenAndLongitudeBetween(String category, double latStart, double latEnd, double lonStart, double lonEnd);

    // 카테고리 제거
    List<Store> findByLatitudeBetweenAndLongitudeBetween(double latStart, double latEnd, double lonStart, double lonEnd);

}
