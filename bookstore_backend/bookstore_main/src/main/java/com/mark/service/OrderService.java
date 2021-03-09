package com.mark.service;

import com.mark.dto.ShoppingCart;
import com.mark.entity.BookInfoInCart;
import com.mark.entity.Order;

import java.sql.Date;
import java.util.List;

public interface OrderService {
    List<Order> getUserOrder(int userId);

    List<Order> getOrders();

    List<BookInfoInCart> getOrderItems(int orderId);
}
