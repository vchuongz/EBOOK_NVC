package com.project.shopapp.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoMoPaymentDTO {
    private String orderId;
    private String requestId;
    private String amount;
    private String orderInfo;
}
