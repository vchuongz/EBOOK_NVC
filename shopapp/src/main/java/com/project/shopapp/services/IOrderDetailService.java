package com.project.shopapp.services;

import com.project.shopapp.dtos.OrderDetailDTO;
import com.project.shopapp.models.OrderDetail;
import com.project.shopapp.exception.DataNotFoundException;
import com.project.shopapp.responses.ProductSalesResponse;

import java.util.List;

public interface IOrderDetailService {
    OrderDetail createOrderDetail(OrderDetailDTO dto) throws Exception;
    OrderDetail getOrderDetail(Long id) throws DataNotFoundException;
    OrderDetail updateOrderDetail(Long id, OrderDetailDTO dto) throws Exception;
    void deleteById(Long id);
    List<OrderDetail> findByOrderId(Long orderId);

    List<ProductSalesResponse> getTopSellingProducts(int limit);
}
