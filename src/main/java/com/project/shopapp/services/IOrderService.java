package com.project.shopapp.services;

import com.project.shopapp.dtos.OrderDTO;
import com.project.shopapp.models.Order;
import java.util.List;

public interface IOrderService {
    Order createOrder(OrderDTO dto) throws Exception;
    Order getOrderById(Long id) throws Exception;
    List<Order> getOrdersByUserId(Long userId);
    Order updateOrder(Long id, OrderDTO dto) throws Exception;
    void deleteOrder(Long id);
}
