package com.project.shopapp.repository;

import com.project.shopapp.models.MoMoPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoMoPaymentRepository extends JpaRepository<MoMoPayment, Long> {
    MoMoPayment findByOrderId(String orderId);
}
