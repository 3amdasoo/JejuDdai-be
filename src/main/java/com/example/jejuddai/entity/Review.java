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

    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;

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
                .created_at(this.created_at)
                .updated_at(this.updated_at)
                .build();
    }
}
