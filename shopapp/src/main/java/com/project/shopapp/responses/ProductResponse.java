package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.Product;
import com.project.shopapp.models.ProductImage;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;


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

    private String author;

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

    @JsonProperty("product_image_urls")
    private List<String> productImageUrls;

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
                .author(product.getAuthor())
                .build();
        productResponse.setCreateAt(product.getCreateAt());
        productResponse.setUpdateAt(product.getUpdateAt());

        if (product.getProductImages() != null) {
            List<String> imageUrls = product.getProductImages()
                    .stream()
                    .map(ProductImage::getImageUrl)
                    .collect(Collectors.toList());
            productResponse.setProductImageUrls(imageUrls);
        }
        return productResponse;
    }
}

