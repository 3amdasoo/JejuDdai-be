package com.example.jejuddai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private Long id;

    private Long user_id; // 유저 id (작성자)

    private String user_nickname; // 유저 닉네임(작성자)

    private String store_id; // 가게 id

    private String store_name; // 가게명

    private int grade; // 등급

    private String content; // 리뷰 내용

    private String imageUrl1; // 리뷰 이미지1 URL

    private String imageUrl2; // 리뷰 이미지2 URL

    private String imageUrl3; // 리뷰 이미지3 URL

    private LocalDateTime createdAt; // 최초 작성 날짜

    private LocalDateTime updatedAt; // 최근 수정 날짜
}
