package com.project.shopapp.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductSalesResponse {
    private Long productId;
    private String productName;
    private Long totalSold;
}
