
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
    private final ProductReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductReviewResponse createReview(ProductReviewDTO dto) throws Exception {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductReview review = ProductReview.builder()
                .user(user)
                .product(product)
                .rating(dto.getRating())
                .comment(dto.getComment())
                .build();

        return ProductReviewResponse.from(reviewRepository.save(review));
    }

    @Override
    public List<ProductReviewResponse> getReviewsByProduct(Long productId) {
        return reviewRepository.findByProductId(productId).stream()
                .map(ProductReviewResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductReviewResponse> getReviewsByUser(Long userId) {
        return reviewRepository.findByUserId(userId).stream()
                .map(ProductReviewResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
