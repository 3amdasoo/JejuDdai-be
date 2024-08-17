package com.example.jejuddai.entity;

import com.example.jejuddai.dto.StoreResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "store")
public class Store {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String address;
    private String phone;
    private String category;
    private double latitude;
    private double longitude;
    private String image;
    private boolean isNotGoodForChild;
    private boolean isFoodSelling;

    public StoreResponseDTO toResponseDTO() {
        return StoreResponseDTO.builder()
                .id(this.id)
                .name(this.name)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .address(this.address)
                .phone(this.phone)
                .category(this.category)
                .image(this.image)
                .isNotGoodForChild(this.isNotGoodForChild)
                .isFoodSelling(this.isFoodSelling)
                .build();
    }
}
