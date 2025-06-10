package com.project.shopapp.services;

import com.project.shopapp.dtos.ShopDTO;
import com.project.shopapp.models.Shop;

import java.util.List;

public interface IShopService {
    Shop createShop(ShopDTO shopDTO);
    Shop getShopById(Long id);
    List<Shop> getAllShops();
    Shop updateShop(Long id, ShopDTO shopDTO);
    void deleteShop(Long id);
    Shop getShopByUserId(Long userId);
}
