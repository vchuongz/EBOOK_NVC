package com.project.shopapp.repository;

import com.project.shopapp.models.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    List<ProductReview> findByProductId(Long productId);
    List<ProductReview> findByUserId(Long userId);
}
