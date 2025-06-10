package com.project.shopapp.services;

import com.project.shopapp.dtos.ProductReviewDTO;
import com.project.shopapp.models.Product;
import com.project.shopapp.models.ProductReview;
import com.project.shopapp.models.User;
import com.project.shopapp.repository.ProductRepository;
import com.project.shopapp.repository.ProductReviewRepository;
import com.project.shopapp.repository.UserRepository;
import com.project.shopapp.responses.ProductReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductReviewService implements IProductReviewService {
    private final ProductReviewRepository productReviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductReviewResponse createReview(ProductReviewDTO dto) throws Exception {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new Exception("User not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new Exception("Product not found"));

        ProductReview review = ProductReview.builder()
                .user(user)
                .product(product)
                .rating(dto.getRating())
                .comment(dto.getComment())
                .build();

        productReviewRepository.save(review);
        return ProductReviewResponse.fromReview(review);
    }

    @Override
    public List<ProductReviewResponse> getReviewsByProductId(Long productId) {
        return productReviewRepository.findByProductId(productId)
                .stream()
                .map(ProductReviewResponse::fromReview)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductReviewResponse> getReviewsByUserId(Long userId) {
        return productReviewRepository.findByUserId(userId)
                .stream()
                .map(ProductReviewResponse::fromReview)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReview(Long id) {
        productReviewRepository.deleteById(id);
    }
}
