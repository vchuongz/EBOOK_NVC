package com.project.shopapp.services;

import com.project.shopapp.dtos.EbookDownloadDTO;
import com.project.shopapp.models.EbookDownload;
import com.project.shopapp.models.Product;
import com.project.shopapp.models.User;
import com.project.shopapp.repository.EbookDownloadRepository;
import com.project.shopapp.repository.ProductRepository;
import com.project.shopapp.repository.UserRepository;
import com.project.shopapp.responses.EbookDownloadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EbookDownloadService implements IEbookDownloadService {
    private final EbookDownloadRepository repository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public EbookDownload createDownload(EbookDownloadDTO dto) throws Exception {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        EbookDownload download = EbookDownload.builder()
                .user(user)
                .product(product)
                .build();
        return repository.save(download);
    }

    @Override
    public List<EbookDownloadResponse> getDownloadsByUser(Long userId) {
        return repository.findByUserId(userId).stream()
                .map(EbookDownloadResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<EbookDownloadResponse> getDownloadsByProduct(Long productId) {
        return repository.findByProductId(productId).stream()
                .map(EbookDownloadResponse::from)
                .collect(Collectors.toList());
    }
}
