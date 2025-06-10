package com.project.shopapp.repository;

import com.project.shopapp.models.EbookDownload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EbookDownloadRepository extends JpaRepository<EbookDownload, Long> {
    List<EbookDownload> findByUserId(Long userId);
    List<EbookDownload> findByProductId(Long productId);
}