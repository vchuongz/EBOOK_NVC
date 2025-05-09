package com.project.shopapp.services;

import com.project.shopapp.dtos.ProductReviewDTO;
import com.project.shopapp.responses.ProductReviewResponse;
import java.util.List;

public interface IProductReviewService {
    ProductReviewResponse createReview(ProductReviewDTO dto) throws Exception;
    List<ProductReviewResponse> getReviewsByProduct(Long productId);
    List<ProductReviewResponse> getReviewsByUser(Long userId);
    void deleteReview(Long id);
}