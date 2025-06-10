package com.project.shopapp.services;

import com.project.shopapp.dtos.EbookDownloadDTO;
import com.project.shopapp.models.EbookDownload;
import com.project.shopapp.responses.EbookDownloadResponse;
import java.util.List;

public interface IEbookDownloadService {
    EbookDownload createDownload(EbookDownloadDTO dto) throws Exception;
    List<EbookDownloadResponse> getDownloadsByUser(Long userId);
    List<EbookDownloadResponse> getDownloadsByProduct(Long productId);
}