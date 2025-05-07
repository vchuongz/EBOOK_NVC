
package com.project.shopapp.controller;

import com.project.shopapp.dtos.EbookDownloadDTO;
import com.project.shopapp.models.EbookDownload;
import com.project.shopapp.responses.EbookDownloadResponse;
import com.project.shopapp.services.IEbookDownloadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/downloads")
@RequiredArgsConstructor
public class EbookDownloadController {
    private final IEbookDownloadService service;

    @PostMapping("")
    public ResponseEntity<EbookDownloadResponse> download(@Valid @RequestBody EbookDownloadDTO dto) throws Exception {
        EbookDownload entity = service.createDownload(dto);
        return ResponseEntity.ok(EbookDownloadResponse.from(entity));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EbookDownloadResponse>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getDownloadsByUser(userId));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<EbookDownloadResponse>> getByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(service.getDownloadsByProduct(productId));
    }
}
