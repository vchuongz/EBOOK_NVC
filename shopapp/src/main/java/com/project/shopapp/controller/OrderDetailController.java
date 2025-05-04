package com.project.shopapp.controller;
import com.project.shopapp.dtos.CategoryDTO;
import com.project.shopapp.dtos.OrderDTO;
import com.project.shopapp.dtos.OrderDetailDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/order_details")
public class OrderDetailController {
    @PostMapping(value = "")
    public ResponseEntity<?> createOrderDetail(@Valid @RequestBody OrderDetailDTO orderDetailDTO
            , BindingResult result
    ){
        try{
            if(result.hasErrors()){
                List<String> errorMessage = result.getFieldErrors()
                        .stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
                return  ResponseEntity.badRequest().body(errorMessage);
            }
            return ResponseEntity.ok().body("createOrderDetail successfuly");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getOrderDetail(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok("OrderDetail with id=" +id);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<String> getOrderDetails(@Valid @PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok("OrderDetail with orderId=" +orderId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrderDetail(@Valid @PathVariable Long id, @RequestBody OrderDetailDTO orderDetailDTO) {
        return ResponseEntity.ok("update OrderDetail successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderDetail(@Valid @PathVariable Long id, @RequestBody OrderDetailDTO orderDetailDTO) {
        return ResponseEntity.ok("delete OrderDetail successfully");
    }
}