package com.project.shopapp.controller;

import com.project.shopapp.dtos.CategoryDTO;
import com.project.shopapp.dtos.OrderDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/orders")
public class OrderController {
    @PostMapping(value = "")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDTO orderDTO
                                        , BindingResult result
    ){
        try{
            if(result.hasErrors()){
                List<String> errorMessage = result.getFieldErrors()
                        .stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
                return  ResponseEntity.badRequest().body(errorMessage);
            }
            return ResponseEntity.ok().body("createOrder successfuly");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{user_id}")
    public ResponseEntity<String> getOrderById(@PathVariable("user_id") String user_id) {
        try{
            return ResponseEntity.ok("Order with id=" +user_id);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@Valid @PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok("delete product successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {

        return ResponseEntity.ok("delete product successfully");
    }


}
