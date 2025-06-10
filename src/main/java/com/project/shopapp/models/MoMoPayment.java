package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "momo_payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoMoPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderId;
    private String requestId;
    private String amount;
    private String orderInfo;
    private String payUrl;
    private String transactionId;
    private LocalDateTime createAt = LocalDateTime.now();
}
