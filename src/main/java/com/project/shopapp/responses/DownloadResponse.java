package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.EbookDownloads;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DownloadResponse {
    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("downloaded_at")
    private LocalDateTime downloadedAt;

    public static DownloadResponse fromEntity(EbookDownloads download) {
        return DownloadResponse.builder()
                .id(download.getId())
                .userId(download.getUser().getId())
                .productId(download.getProduct().getId())
                .downloadedAt(download.getDownloadedAt())
                .build();
    }
}
