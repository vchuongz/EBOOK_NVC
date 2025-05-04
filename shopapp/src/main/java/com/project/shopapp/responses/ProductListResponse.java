package com.project.shopapp.responses;


import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Builder
public class ProductListResponse {
    private List<ProductResponse> product;
    private int totalPage;
}
