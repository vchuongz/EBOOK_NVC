package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.Product;
import com.project.shopapp.models.ProductImage;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse extends BaseResponse {
    private Long id;
    private String name;
    private Float price;
    private String thumbnail;
    private String description;

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("shop_id")
    private Long shopId;

    @JsonProperty("file_url")
    private String fileUrl;

    @JsonProperty("file_format")
    private String fileFormat;

    @JsonProperty("is_downloadable")
    private Boolean isDownloadable;

    @JsonProperty("product_image")
    private ProductImage productImage;

    public static ProductResponse fromProduct(Product product) {
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .thumbnail(product.getThumbnail())
                .description(product.getDescription())
                .categoryId(product.getCategoriesId().getId())
                .shopId(product.getShop().getId())
                .fileUrl(product.getFileUrl())
                .fileFormat(product.getFileFormat())
                .isDownloadable(product.getIsDownloadable())
                .build();
        productResponse.setCreateAt(product.getCreateAt());
        productResponse.setUpdateAt(product.getUpdateAt());
        return productResponse;
    }
}

