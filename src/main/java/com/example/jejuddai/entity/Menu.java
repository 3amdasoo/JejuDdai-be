package com.example.jejuddai.entity;

import com.example.jejuddai.dto.MenuResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id; // 메뉴 아이디

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store; // 판매 가게 아이디

    private String name; // 메뉴 이름
    private String price; // 메뉴 금액

    public MenuResponseDTO toResponseDTO() {
        return MenuResponseDTO.builder()
                .id(this.id) // 메뉴 아이디
                .name(this.name) // 메뉴 이름
                .price(this.price) // 메뉴 가격
                .store_id(this.store.getId()) // 가게 아이디
                .store_name(this.store.getName()) // 가게 이름
                .build();
    }
}
