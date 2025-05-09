package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductReviewDTO {
    @JsonProperty("user_id")
    @NotNull
    private Long userId;

    @JsonProperty("product_id")
    @NotNull
    private Long productId;

    @Min(1)
    @Max(5)
    private int rating;

    private String comment;
}