package com.project.shopapp.services;

import com.project.shopapp.dtos.MoMoPaymentDTO;
import com.project.shopapp.models.MoMoPayment;
import com.project.shopapp.repository.MoMoPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MoMoPaymentService {
    private final MoMoPaymentRepository paymentRepository;

    @Value("${momo.partner-code}")
    private String partnerCode;

    @Value("${momo.access-key}")
    private String accessKey;

    @Value("${momo.secret-key}")
    private String secretKey;

    @Value("${momo.endpoint-url}")
    private String endpointUrl;

    @Value("${momo.return-url}")
    private String returnUrl;

    @Value("${momo.notify-url}")
    private String notifyUrl;

    public MoMoPayment createPayment(MoMoPaymentDTO dto) throws Exception {
        String requestId = String.valueOf(System.currentTimeMillis());
        String orderId = dto.getOrderId();
        String requestRawData = "accessKey=" + accessKey +
                "&amount=" + dto.getAmount() +
                "&extraData=" +
                "&notifyUrl=" + notifyUrl +  // Đúng phải là notifyUrl
                "&orderId=" + dto.getOrderId() +
                "&orderInfo=" + dto.getOrderInfo() +
                "&partnerCode=" + partnerCode +
                "&redirectUrl=" + returnUrl +  // Đảm bảo URL này không bị trống
                "&requestId=" + requestId +
                "&requestType=captureWallet";

        String signature = generateSignature(requestRawData);

        Map<String, Object> requestData = new HashMap<>();
        requestData.put("partnerCode", partnerCode);
        requestData.put("accessKey", accessKey);
        requestData.put("requestId", requestId);
        requestData.put("orderId", orderId);
        requestData.put("orderInfo", dto.getOrderInfo());
        requestData.put("amount", dto.getAmount());
        requestData.put("returnUrl", returnUrl);
//        requestData.put("notifyUrl", notifyUrl);
        requestData.put("ipnUrl", notifyUrl);
        requestData.put("extraData", "");
        requestData.put("requestType", "captureWallet");
        requestData.put("signature", signature);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestData, headers);
        ResponseEntity<Map> response = restTemplate.exchange(endpointUrl, HttpMethod.POST, entity, Map.class);
        Map<String, Object> responseBody = response.getBody();

        MoMoPayment payment = MoMoPayment.builder()
                .orderId(orderId)
                .requestId(requestId)
                .amount(dto.getAmount())
                .orderInfo(dto.getOrderInfo())
                .payUrl((String) responseBody.get("payUrl"))
                .build();

        return paymentRepository.save(payment);
    }

    private String generateSignature(String rawData) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(rawData.getBytes()));
    }
}
