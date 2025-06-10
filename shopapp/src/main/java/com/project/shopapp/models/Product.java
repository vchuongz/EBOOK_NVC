
package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Float price;
    private String thumbnail;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "file_format")
    private String fileFormat;

    @Column(name = "is_downloadable")
    private Boolean isDownloadable;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Category categoriesId;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> productImages;

    private String author;
}
