package com.example.jejuddai.repository;

import com.example.jejuddai.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
