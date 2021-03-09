package com.mark.service;

import com.mark.dto.ShoppingCart;
import com.mark.entity.BookInfoInCart;
import com.mark.entity.Order;

import java.sql.Date;
import java.util.List;

public interface OrderService {
    void addOrder(Date orderDate, ShoppingCart shoppingCart);

    List<Order> getUserOrder(int userId);

    List<Order> getOrders();

    List<BookInfoInCart> getOrderItems(int orderId);
}
