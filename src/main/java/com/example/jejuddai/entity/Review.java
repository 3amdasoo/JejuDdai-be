package com.example.jejuddai.entity;

import com.example.jejuddai.dto.ReviewDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private int grade;

    private String content;

    @Column(name = "image_url1") // 데이터베이스의 컬럼명
    private String imageUrl1;    // 엔티티의 변수명

    @Column(name = "image_url2")
    private String imageUrl2;

    @Column(name = "image_url3")
    private String imageUrl3;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public ReviewDTO toResponseDTO() {
        return ReviewDTO.builder()
                .id(this.id)
                .store_id(this.store.getId())
                .store_name(this.store.getName())
                .user_id(this.user.getId())
                .user_nickname(this.user.getNickname())
                .grade(this.grade)
                .content(this.content)
                .imageUrl1(this.imageUrl1)
                .imageUrl2(this.imageUrl2)
                .imageUrl3(this.imageUrl3)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
