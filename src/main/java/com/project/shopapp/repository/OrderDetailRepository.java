package com.project.shopapp.repository;

import com.project.shopapp.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Long orderId);
    @Query("SELECT d.product, SUM(d.numberOfProducts) AS totalSold " +
            "FROM OrderDetail d " +
            "GROUP BY d.product " +
            "ORDER BY totalSold DESC")
    List<Object[]> findTopSellingProducts(@Param("limit") int limit);
}
