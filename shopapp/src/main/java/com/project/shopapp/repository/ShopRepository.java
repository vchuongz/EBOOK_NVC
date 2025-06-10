package com.project.shopapp.repository;

import com.project.shopapp.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Shop findByUserId(Long userId);
}
