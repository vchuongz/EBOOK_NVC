package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String fullname;
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String notes;

    @Column(name = "order_date")
    private LocalDateTime orderDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "total_money")
    private Float totalMoney;

    @Column(name = "payment_method")
    private String paymentMethod;

    private Boolean active;
}

