package com.project.shopapp.responses;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoMoPaymentResponse {
    private int status;
    private String message;
    private String payUrl;
}