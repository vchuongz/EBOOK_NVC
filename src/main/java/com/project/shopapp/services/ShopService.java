package com.project.shopapp.services;

import com.project.shopapp.dtos.ShopDTO;
import com.project.shopapp.models.Shop;
import com.project.shopapp.models.User;
import com.project.shopapp.repository.ShopRepository;
import com.project.shopapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService implements IShopService {
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;

    @Override
    public Shop createShop(ShopDTO shopDTO) {
        User userId = userRepository.findById(shopDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Shop shop = Shop.builder()
                .name(shopDTO.getName())
                .description(shopDTO.getDescription())
                .user(userId)
                .active(shopDTO.getIsActive())
                .build();
        return shopRepository.save(shop);
    }

    @Override
    public Shop getShopById(Long id) {
        return shopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shop not found"));
    }

    @Override
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    @Override
    public Shop getShopByUserId(Long userId) {
        return shopRepository.findByUserId(userId);  // Tìm shop theo userId
    }


    @Override
    public Shop updateShop(Long id, ShopDTO shopDTO) {
        Shop shop = getShopById(id);
        shop.setName(shopDTO.getName());
        shop.setDescription(shopDTO.getDescription());
        shop.setActive(shopDTO.getIsActive());
//        if (shopDTO.getUserId() != null) {
//            User user = userRepository.findById(shopDTO.getUserId())
//                    .orElseThrow(() -> new RuntimeException("User not found"));
//            shop.setUser(user);
//        }
        return shopRepository.save(shop);
    }

    @Override
    public void deleteShop(Long id) {
        shopRepository.deleteById(id);
    }
}
