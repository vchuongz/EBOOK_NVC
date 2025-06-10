package com.project.shopapp.services;

import com.project.shopapp.dtos.ProductDTO;
import com.project.shopapp.dtos.ProductImageDTO;
import com.project.shopapp.exception.DataNotFoundException;
import com.project.shopapp.exception.InvalidParamException;
import com.project.shopapp.models.Category;
import com.project.shopapp.models.Product;
import com.project.shopapp.models.ProductImage;
import com.project.shopapp.models.Shop;
import com.project.shopapp.repository.CategoryRepository;
import com.project.shopapp.repository.ProductImageRepository;
import com.project.shopapp.repository.ProductRepository;
import com.project.shopapp.repository.ShopRepository;
import com.project.shopapp.responses.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ShopRepository shopRepository;

    private final ProductImageRepository productImageRepository;

    @Override
    public Product createProduct(ProductDTO productDTO) throws DataNotFoundException{
        Category existingCategory = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(()->new DataNotFoundException("Cannot find category with id"));
        Shop shop = shopRepository.findById(productDTO.getShopId())
                .orElseThrow(() -> new RuntimeException("Shop not found"));
        Product newProduct =Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .thumbnail(productDTO.getThumbnail())
                .description(productDTO.getDescription())
                .fileUrl(productDTO.getFileUrl())
                .fileFormat(productDTO.getFileFormat())
                .isDownloadable(productDTO.getIsDownloadable())
                .author(productDTO.getAuthor())
                .categoriesId(existingCategory)
                .shop(shop)
                .build();
        return productRepository.save(newProduct);
    }

    @Override
    public Product getProductById(long productId) throws  DataNotFoundException{
        return productRepository.findById(productId)
                .orElseThrow(()->new DataNotFoundException(
                        " Cannot find product with id = "+ productId
                ));
    }

    @Override
    public Page<ProductResponse> getAllProduct(PageRequest pageRequest) {
        return productRepository.findAll(pageRequest).map(
        product -> {
            ProductResponse productResponses=
                    ProductResponse.builder()
                            .id(product.getId())
                            .name(product.getName())
                            .price(product.getPrice())
                            .thumbnail(product.getThumbnail())
                            .description(product.getDescription())
                            .fileUrl(product.getFileUrl())
                            .fileFormat(product.getFileFormat())
                            .isDownloadable(product.getIsDownloadable())
                            .author(product.getAuthor())
                            .categoryId(product.getCategoriesId().getId())
                            .shopId(product.getShop().getId())
                            .build();
            productResponses.setCreateAt(product.getCreateAt());
            productResponses.setUpdateAt(product.getUpdateAt());
            return productResponses;
        }
        );
    }

    @Override
    public Product updateProduct(long id, ProductDTO productDTO) throws  Exception{
//        Category category = categoryRepository.findById(productDTO.getCategoryId())
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//
//        Shop shop = shopRepository.findById(productDTO.getShopId())
//                .orElseThrow(() -> new RuntimeException("Shop not found"));

        Product existingProduct = getProductById(id);
        if(existingProduct!= null){
            Category existingCategory = categoryRepository.findById(productDTO.getCategoryId())
                            .orElseThrow(()-> new DataNotFoundException("cannot find"));
            existingProduct.setName(productDTO.getName());
            existingProduct.setCategoriesId(existingCategory);
            existingProduct.setPrice(productDTO.getPrice());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setThumbnail(productDTO.getThumbnail());
            existingProduct.setFileUrl(productDTO.getFileUrl());
            existingProduct.setFileFormat(productDTO.getFileFormat());
            existingProduct.setIsDownloadable(productDTO.getIsDownloadable());
            existingProduct.setAuthor(productDTO.getAuthor());
//            existingProduct.setCategoriesId(category);
//            existingProduct.setShop(shop);
            productRepository.save(existingProduct);
            return existingProduct;
        }
        return null;
    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            productRepository.deleteById(optionalProduct.get().getId());
        }
    }

    @Override
    public boolean existsByName(String name) throws Exception{
        return productRepository.existsByName(name);
    }
    @Override
    public ProductImage createProductImage(
            Long ProductId,
            ProductImageDTO productImageDTO) throws Exception {
        Product existingProduct =productRepository
                .findById(ProductId)
                .orElseThrow(()-> new DataNotFoundException(
                        "Cannot find"
                ));
        ProductImage newProductImage =ProductImage.builder()
                .product(existingProduct)
                .imageUrl(productImageDTO.getImageUrl())
                .build();
        int size = productImageRepository.findByProductId(ProductId).size();
        if(size>= ProductImage.MAXIMUM_IMAGES_PER_PRODUCT){
            throw new InvalidParamException(
                    "Number of images must be <= "
                            +ProductImage.MAXIMUM_IMAGES_PER_PRODUCT);
        }
        return productImageRepository.save(newProductImage);
    }

    @Override
    public List<ProductResponse> getProductsByShopId(Long shopId) {
        List<Product> products = productRepository.findByShopId(shopId);
        return products.stream()
                .map(ProductResponse::fromProduct)
                .toList();
    }

    @Override
    public List<ProductResponse> searchProductsByName(String keyword) {
        List<Product> products = productRepository.searchByName(keyword);
        return products.stream()
                .map(ProductResponse::fromProduct)
                .collect(Collectors.toList());
    }

}
