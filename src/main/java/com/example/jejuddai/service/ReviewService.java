package com.example.jejuddai.service;

import com.example.jejuddai.FileStorageUtil;
import com.example.jejuddai.dto.ReviewDTO;
import com.example.jejuddai.entity.Review;
import com.example.jejuddai.entity.Store;
import com.example.jejuddai.entity.User;
import com.example.jejuddai.repository.ReviewRepository;
import com.example.jejuddai.repository.StoreRepository;
import com.example.jejuddai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private FileStorageUtil fileStorageUtil;

    // 신규 리뷰 작성 + 이미지
    public ReviewDTO saveReview(ReviewDTO reviewDTO, MultipartFile image1, MultipartFile image2, MultipartFile image3) throws IOException {
        Long userId = reviewDTO.getUser_id();
        String storeId = reviewDTO.getStore_id();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid store ID"));

        Review review = Review.builder()
                .user(user)
                .store(store)
                .content(reviewDTO.getContent())
                .grade(reviewDTO.getGrade())

                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();

        review = reviewRepository.save(review);

        if (image1 != null) {
            String imageUrl1 = fileStorageUtil.storeFile(image1, "review_" + review.getId() + "_1_" + image1.getOriginalFilename());
            review.setImageUrl1(imageUrl1);
        }
        if (image2 != null) {
            String imageUrl2 = fileStorageUtil.storeFile(image2, "review_" + review.getId() + "_2_" + image2.getOriginalFilename());
            review.setImageUrl2(imageUrl2);
        }
        if (image3 != null) {
            String imageUrl3 = fileStorageUtil.storeFile(image3, "review_" + review.getId() + "_3_" + image3.getOriginalFilename());
            review.setImageUrl3(imageUrl3);
        }

        review = reviewRepository.save(review);

        return ReviewDTO.builder()
                .id(review.getId())
                .user_id(review.getUser().getId())
                .store_id(review.getStore().getId())
                .content(review.getContent())
                .grade(review.getGrade())
                .imageUrl1(review.getImageUrl1())
                .imageUrl2(review.getImageUrl2())
                .imageUrl3(review.getImageUrl3())
                .created_at(review.getCreated_at())
                .updated_at(review.getUpdated_at())
                .build();

    }

    // 리뷰 조회
    public Optional<ReviewDTO> getReviewById(Long id) {

        return reviewRepository.findById(id).map(review -> ReviewDTO.builder()
                .id(review.getId())
                .user_id(review.getUser().getId())
                .store_id(review.getStore().getId())
                .content(review.getContent())
                .grade(review.getGrade())
                .imageUrl1(review.getImageUrl1())
                .imageUrl2(review.getImageUrl2())
                .imageUrl3(review.getImageUrl3())
                .created_at(review.getCreated_at())
                .updated_at(review.getUpdated_at())
                .build());
    }

    // 리뷰 수정
    public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO, MultipartFile image1, MultipartFile image2, MultipartFile image3) throws IOException {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid review ID"));

        review.setContent(reviewDTO.getContent());
        review.setGrade(reviewDTO.getGrade());

        if (image1 != null) {
            String imageUrl1 = fileStorageUtil.storeFile(image1, "review_" + review.getId() + "_1_" + image1.getOriginalFilename());
            review.setImageUrl1(imageUrl1);
        }
        if (image2 != null) {
            String imageUrl2 = fileStorageUtil.storeFile(image2, "review_" + review.getId() + "_2_" + image2.getOriginalFilename());
            review.setImageUrl2(imageUrl2);
        }
        if (image3 != null) {
            String imageUrl3 = fileStorageUtil.storeFile(image3, "review_" + review.getId() + "_3_" + image3.getOriginalFilename());
            review.setImageUrl3(imageUrl3);
        }

        review.setUpdated_at(LocalDateTime.now());

        review = reviewRepository.save(review);

        return mapToDTO(review);
    }


    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    // 이미지 파일을 바이트 배열로 변환하거나 null을 반환하는 헬퍼 메서드
    private byte[] getBytesOrNull(MultipartFile file) throws IOException {
        return (file != null && !file.isEmpty()) ? file.getBytes() : null;
    }

    // Review 엔티티를 ReviewDTO로 변환하는 헬퍼 메서드
    private ReviewDTO mapToDTO(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .user_id(review.getUser().getId())
                .store_id(review.getStore().getId())
                .content(review.getContent())
                .grade(review.getGrade())
                .imageUrl1(review.getImageUrl1())
                .imageUrl2(review.getImageUrl2())
                .imageUrl3(review.getImageUrl3())
                .created_at(review.getCreated_at())
                .updated_at(review.getUpdated_at())
                .build();
    }


    // 리뷰 삭제
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
