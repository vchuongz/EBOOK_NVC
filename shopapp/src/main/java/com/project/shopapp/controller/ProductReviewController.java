
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

    @PostMapping("/create")
    public ResponseEntity<ProductReviewResponse> createReview(@Valid @RequestBody ProductReviewDTO dto) throws Exception {
        System.out.println("Creating review: " + dto);
        return ResponseEntity.ok(reviewService.createReview(dto));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductReviewResponse>> getByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.getReviewsByProduct(productId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductReviewResponse>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(reviewService.getReviewsByUser(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review deleted successfully");
    }
}
