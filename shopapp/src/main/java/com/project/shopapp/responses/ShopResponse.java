package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.Shop;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopResponse extends BaseResponse {
    private Long id;

    private String name;

    private String description;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("is_active")
    private Boolean isActive;

    public static ShopResponse fromShop(Shop shop) {
        ShopResponse shopResponse = ShopResponse.builder()
                .id(shop.getId())
                .name(shop.getName())
                .description(shop.getDescription())
                .userId(shop.getUser() != null ? shop.getUser().getId() : null)
                .isActive(Boolean.TRUE.equals(shop.getActive()))
                .build();
        shopResponse.setCreateAt(shop.getCreateAt());
        shopResponse.setUpdateAt(shop.getUpdateAt());
        return shopResponse;
    }

}
