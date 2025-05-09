package com.project.shopapp.controller;

import com.project.shopapp.dtos.OrderDetailDTO;
import com.project.shopapp.models.OrderDetail;
import com.project.shopapp.responses.OrderDetailResponse;
import com.project.shopapp.responses.ProductSalesResponse;
import com.project.shopapp.services.IOrderDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.prefix}/order_details")
@RequiredArgsConstructor
public class OrderDetailController {
    private final IOrderDetailService orderDetailService;

    @PostMapping("")
    public ResponseEntity<?> createOrderDetail(@Valid @RequestBody OrderDetailDTO dto) {
        try {
            OrderDetail detail = orderDetailService.createOrderDetail(dto);
            return ResponseEntity.ok(OrderDetailResponse.fromOrderDetail(detail));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(@PathVariable Long id) {
        try {
            OrderDetail detail = orderDetailService.getOrderDetail(id);
            return ResponseEntity.ok(OrderDetailResponse.fromOrderDetail(detail));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderDetailResponse>> getByOrderId(@PathVariable Long orderId) {
        List<OrderDetailResponse> responses = orderDetailService.findByOrderId(orderId)
                .stream().map(OrderDetailResponse::fromOrderDetail).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderDetail(@PathVariable Long id,
                                               @Valid @RequestBody OrderDetailDTO dto) {
        try {
            OrderDetail updated = orderDetailService.updateOrderDetail(id, dto);
            return ResponseEntity.ok(OrderDetailResponse.fromOrderDetail(updated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderDetail(@PathVariable Long id) {
        orderDetailService.deleteById(id);
        return ResponseEntity.ok("Order detail deleted successfully");
    }

    @GetMapping("/top_selling")
    public ResponseEntity<List<ProductSalesResponse>> getTopSellingProducts(
            @RequestParam(name = "limit", defaultValue = "10") int limit) {
        List<ProductSalesResponse> topProducts = orderDetailService.getTopSellingProducts(limit);
        return ResponseEntity.ok(topProducts);
    }

}
