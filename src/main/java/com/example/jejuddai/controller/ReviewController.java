package com.example.jejuddai.controller;

import com.example.jejuddai.dto.ReviewDTO;
import com.example.jejuddai.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    // 신규 리뷰 작성 + 이미지
    @PostMapping("/upload")
    public ResponseEntity<ReviewDTO> uploadReview(
            @RequestParam("user_id") Long userId,
            @RequestParam("store_id") String storeId,
            @RequestParam("content") String content,
            @RequestParam("grade") int grade,
            @RequestParam(value = "image1", required = false) MultipartFile image1,
            @RequestParam(value = "image2", required = false) MultipartFile image2,
            @RequestParam(value = "image3", required = false) MultipartFile image3) {
        try {
            ReviewDTO reviewDTO = ReviewDTO.builder()
                    .user_id(userId)
                    .store_id(storeId)
                    .content(content)
                    .grade(grade)
                    .build();

            ReviewDTO savedReview = reviewService.saveReview(reviewDTO, image1, image2, image3);
            return new ResponseEntity<>(savedReview, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 리뷰 조회 (업체별)
//    @GetMapping
//    public ResponseEntity<List<Review>> getAllReviews() {
//        List<Review> reviews = reviewService.getAllReviews();
//        return new ResponseEntity<>(reviews, HttpStatus.OK);
//    }

    // 특정 리뷰 조회
    @GetMapping("/get/{id}")
    public ResponseEntity<ReviewDTO> getReview(@PathVariable Long id) {
        Optional<ReviewDTO> reviewDTO = reviewService.getReviewById(id);
        return reviewDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // 리뷰 수정
    @PutMapping("/modify/{id}")
    public ResponseEntity<ReviewDTO> updateReview(
            @PathVariable Long id,
            @RequestParam("content") String content,
            @RequestParam("grade") int grade,
            @RequestParam(value = "image1", required = false) MultipartFile image1,
            @RequestParam(value = "image2", required = false) MultipartFile image2,
            @RequestParam(value = "image3", required = false) MultipartFile image3) {
        try {
            ReviewDTO reviewDTO = ReviewDTO.builder()
                    .content(content)
                    .grade(grade)
                    .build();

            ReviewDTO updatedReview = reviewService.updateReview(id, reviewDTO, image1, image2, image3);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 리뷰 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
