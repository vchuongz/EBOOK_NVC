package com.project.shopapp.controller;

import com.project.shopapp.dtos.MoMoPaymentDTO;
import com.project.shopapp.models.MoMoPayment;
import com.project.shopapp.services.MoMoPaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("${api.prefix}/payments/momo")
@RequiredArgsConstructor
public class MoMoPaymentController {
    private final MoMoPaymentService paymentService;

    @PostMapping("")
    public ResponseEntity<?> createPayment(@Valid @RequestBody MoMoPaymentDTO dto) {
        System.out.println("✅ MoMo Payment API hit");

        try {
            System.out.println("✅ MoMo Payment API hit: " + dto);

            MoMoPayment payment = paymentService.createPayment(dto);
            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/success")
    public ResponseEntity<?> paymentSuccess(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok("Payment successful! " + params);
    }

    @PostMapping("/notify")
    public ResponseEntity<?> paymentNotify(@RequestBody Map<String, Object> notifyData) {
        System.out.println("Notify: " + notifyData);
        return ResponseEntity.ok("Notification received");
    }
}
