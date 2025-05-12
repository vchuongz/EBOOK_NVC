package com.project.shopapp.controller;

import com.project.shopapp.dtos.ProductReviewDTO;
import com.project.shopapp.responses.ProductReviewResponse;
import com.project.shopapp.services.IProductReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/reviews")
@RequiredArgsConstructor
public class ProductReviewController {
    private final IProductReviewService reviewService;

    @PostMapping("")
    public ResponseEntity<?> createReview(@Valid @RequestBody ProductReviewDTO dto) {
        try {
            return ResponseEntity.ok(reviewService.createReview(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductReviewResponse>> getReviewsByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.getReviewsByProductId(productId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductReviewResponse>> getReviewsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review deleted successfully");
    }
}
