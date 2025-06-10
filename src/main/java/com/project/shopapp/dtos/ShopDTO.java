package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopDTO {

    @NotBlank(message = "Shop name cannot be empty")
    @Size(max = 255, message = "Shop name must be less than 255 characters")
    private String name;


    @Size(max = 1000, message = "Description must be less than 1000 characters")
    private String description;

    @JsonProperty("user_id")
//    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @JsonProperty("is_active")
    private Boolean isActive = true;
}
