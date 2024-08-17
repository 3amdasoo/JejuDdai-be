package com.example.jejuddai.repository;

import com.example.jejuddai.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, String> {
//    List<Store> findByCategoryAndLatitudeBetweenAndLongitudeBetween(String category, double latStart, double latEnd, double lonStart, double lonEnd);

    // 카테고리 제거
    List<Store> findByLatitudeBetweenAndLongitudeBetween(double latStart, double latEnd, double lonStart, double lonEnd);

    // isNotGoodForChild가 false이고, isFoodSelling이 true인 Store 엔티티 리스트를 반환
//    List<Store> findByIsNotGoodForChildFalseAndIsFoodSellingTrue();

//    List<Store> findByLatitudeBetweenAndLongitudeBetweenAndIsNotGoodForChildFalseAndIsFoodSellingTrue(
//        double latStart, double latEnd, double lonStart, double lonEnd);


}
