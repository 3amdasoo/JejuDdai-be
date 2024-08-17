package com.example.jejuddai.controller;

import com.example.jejuddai.dto.StoreMenuResponseDTO;
import com.example.jejuddai.dto.StoreResponseDTO;
import com.example.jejuddai.entity.Menu;
import com.example.jejuddai.entity.Review;
import com.example.jejuddai.entity.Store;
import com.example.jejuddai.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    // 위치 기반 스토어 정보 전달
    @GetMapping("/search")
    public List<Store> getStoresByCategoryAndLocation(
//        @RequestParam(value = "category") String category,
            @RequestParam(value = "latitude") double latitude,
            @RequestParam(value = "longitude") double longitude,
            @RequestParam(value = "range") double range) {

        return storeService.getStoreByCategoryAndLocation(latitude, longitude, range);

    }

    // 스토어 메뉴 정보 전달
    // 스토어와 메뉴 정보 전달
    // 스토어와 메뉴 정보 전달
    @GetMapping("/menu")
    public ResponseEntity<StoreMenuResponseDTO> getStoreWithMenus(@RequestParam(value = "storeId") String storeId) {
        Store store = storeService.findById(storeId); // Store 정보를 가져옴
        List<Menu> menus = storeService.getMenuByStoreId(storeId); // 해당 가게의 메뉴 정보를 가져옴

        // StoreMenuResponseDTO 생성
        StoreMenuResponseDTO response = StoreMenuResponseDTO.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .storeAddress(store.getAddress())
                .storePhone(store.getPhone())
                .category(store.getCategory())
                .latitude(store.getLatitude())
                .longitude(store.getLongitude())
                .menus(menus) // 메뉴가 없는 경우에도 빈 리스트가 포함됨
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    // 스토어 리뷰 정보 전달
    @GetMapping("/review")
    public List<Review> getReviewsByStoreId(@RequestParam(value = "storeId") String storeId) {
        return storeService.getReviewByStoreId(storeId);
    }

}
