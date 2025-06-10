package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.EbookDownload;
import com.project.shopapp.models.Product;
import com.project.shopapp.models.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EbookDownloadResponse {
    private Long id;

    @JsonProperty("user_id")
    private User user;

    @JsonProperty("product_id")
    private Product product;

    @JsonProperty("downloaded_at")
    private LocalDateTime downloadedAt;

    public static EbookDownloadResponse from(EbookDownload ebookDownload) {
        return EbookDownloadResponse.builder()
                .id(ebookDownload.getId())
                .user(ebookDownload.getUser())
                .product(ebookDownload.getProduct())
                .downloadedAt(ebookDownload.getDownloadedAt())
                .build();
    }
}