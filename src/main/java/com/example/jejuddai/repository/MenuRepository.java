package com.example.jejuddai.repository;

import com.example.jejuddai.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, String> {
    List<Menu> findByStoreId(String storeId);
}
