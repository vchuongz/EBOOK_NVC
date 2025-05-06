package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.OrderDetail;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailResponse {
    private Long id;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("product_id")
    private Long productId;

    private Float price;

    @JsonProperty("number_of_products")
    private int numberOfProducts;

    @JsonProperty("total_money")
    private Float totalMoney;

    public static OrderDetailResponse fromOrderDetail(OrderDetail detail) {
        return OrderDetailResponse.builder()
                .id(detail.getId())
                .orderId(detail.getOrder().getId())
                .productId(detail.getProduct().getId())
                .price(detail.getPrice())
                .numberOfProducts(detail.getNumberOfProducts())
                .totalMoney(detail.getTotalMoney())
                .build();
    }
}
