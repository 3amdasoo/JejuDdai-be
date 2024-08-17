package com.example.jejuddai.dto;

import com.example.jejuddai.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreMenuResponseDTO {
    private String storeId;
    private String storeName;
    private String storeAddress;
    private String storePhone;
    private String category;
    private double latitude;
    private double longitude;
    private List<Menu> menus; // 메뉴 리스트
}
