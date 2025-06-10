package com.project.shopapp.controller;

import com.project.shopapp.components.LocalizationUtils;
import com.project.shopapp.dtos.ShopDTO;
import com.project.shopapp.models.Shop;
import com.project.shopapp.responses.ShopResponse;
import com.project.shopapp.responses.UpdateShopResponse;
import com.project.shopapp.services.ShopService;
import com.project.shopapp.utils.MessageKeys;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.prefix}/shops")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;
    private final LocalizationUtils localizationUtils;

    @PostMapping("")
    public ResponseEntity<?> createShop(
            @Valid @RequestBody ShopDTO shopDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errorMessages);
        }
        Shop shop = shopService.createShop(shopDTO);
        ShopResponse shopResponse = ShopResponse.fromShop(shop);
        return ResponseEntity.ok(shopResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopResponse> getShopById(@PathVariable Long id) {
        Shop shop = shopService.getShopById(id);
        ShopResponse shopResponse = ShopResponse.fromShop(shop);
        return ResponseEntity.ok(shopResponse);
    }

    @GetMapping("")
    public ResponseEntity<List<ShopResponse>> getAllShops() {
        List<Shop> shops = shopService.getAllShops();
        List<ShopResponse> shopResponses = shops.stream()
                .map(ShopResponse::fromShop)
                .collect(Collectors.toList());
        return ResponseEntity.ok(shopResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateShopResponse> updateShop(
            @PathVariable Long id,
            @Valid @RequestBody ShopDTO shopDTO,
            BindingResult bindingResult
    ) {
        UpdateShopResponse updateShopResponse = new UpdateShopResponse();
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());
            updateShopResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.UPDATE_SHOP_FAILED));
            updateShopResponse.setErrors(errorMessages);
            return ResponseEntity.badRequest().body(updateShopResponse);
        }
        shopService.updateShop(id, shopDTO);
        updateShopResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.UPDATE_SHOP_SUCCESSFULLY));
        return ResponseEntity.ok(updateShopResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteShop(@PathVariable Long id) {
        shopService.deleteShop(id);
        return ResponseEntity.ok(localizationUtils.getLocalizedMessage(MessageKeys.DELETE_SHOP_SUCCESSFULLY));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ShopResponse> getShopByUserId(@PathVariable Long userId) {
        Shop shop = shopService.getShopByUserId(userId);
        if (shop == null) {
            return ResponseEntity.notFound().build();
        }
        ShopResponse shopResponse = ShopResponse.fromShop(shop);
        return ResponseEntity.ok(shopResponse);
    }

}
