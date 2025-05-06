package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.OrderStatus;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    private String fullname;
    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String notes;
    private OrderStatus status;

    @JsonProperty("total_money")
    private Float totalMoney;

    @JsonProperty("payment_method")
    private String paymentMethod;

    private Boolean active;

    public static OrderResponse fromOrder(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .fullname(order.getFullname())
                .email(order.getEmail())
                .phoneNumber(order.getPhoneNumber())
                .notes(order.getNotes())
                .status(order.getStatus())
                .totalMoney(order.getTotalMoney())
                .paymentMethod(order.getPaymentMethod())
                .active(order.getActive())
                .build();
    }
}
