package com.reins.bookstore.repository;

import com.reins.bookstore.entity.OrderItem;
import com.reins.bookstore.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    @Query(value = "from OrderItem where orderId = :orderId")
    List<OrderItem> getOrderItems(@Param("orderId") Integer orderId);
}
