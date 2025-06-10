package com.project.shopapp.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private int id;

    @NotBlank(message = "Product name is required")
    private String name;

    @Min(value = 0, message = "Price must be >= 0")
    private Float price;

    private String thumbnail;

    private String description;

    @JsonProperty("file_url")
    private String fileUrl;

    @JsonProperty("file_format")
    private String fileFormat;

    @JsonProperty("is_downloadable")
    private Boolean isDownloadable = true;

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("shop_id")
    private Long shopId;

    private String author;
}
