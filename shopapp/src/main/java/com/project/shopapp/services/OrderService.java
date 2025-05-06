
package com.project.shopapp.services;

import com.project.shopapp.dtos.OrderDTO;
import com.project.shopapp.models.Order;
import com.project.shopapp.models.User;
import com.project.shopapp.repository.OrderRepository;
import com.project.shopapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public Order createOrder(OrderDTO dto) throws Exception {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = Order.builder()
                .user(user)
                .fullname(dto.getFullname())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .notes(dto.getNotes())
                .status(dto.getStatus())
                .totalMoney(dto.getTotalMoney())
                .paymentMethod(dto.getPaymentMethod())
                .active(dto.getActive())
                .build();
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) throws Exception {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Order updateOrder(Long id, OrderDTO dto) throws Exception {
        Order order = getOrderById(id);
        order.setFullname(dto.getFullname());
        order.setEmail(dto.getEmail());
        order.setPhoneNumber(dto.getPhoneNumber());
        order.setNotes(dto.getNotes());
        order.setStatus(dto.getStatus());
        order.setTotalMoney(dto.getTotalMoney());
        order.setPaymentMethod(dto.getPaymentMethod());
        order.setActive(dto.getActive());
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
