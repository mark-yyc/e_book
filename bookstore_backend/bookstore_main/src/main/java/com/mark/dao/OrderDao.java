package com.mark.dao;

import com.mark.dto.ShoppingCart;
import com.mark.entity.Order;
import com.mark.entity.OrderItem;

import java.sql.Date;
import java.util.List;

public interface OrderDao {

    List<Order> getUserOrder(int userId);

    List<Order> getOrders();

    List<OrderItem> getOrderItems(int orderId);
}
