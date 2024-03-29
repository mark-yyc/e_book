package com.mark.repository;

import com.mark.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query("select o from Order o")
    List<Order> getOrders();

    @Query(value = "from Order where userId = :userId")
    List<Order> getUserOrder(@Param("userId") Integer userId);

}
