package com.mark.bookstore.dao;

import com.mark.bookstore.dto.ShoppingCartSession;
import com.mark.bookstore.entity.Order;
import com.mark.bookstore.entity.OrderItem;

import java.sql.Date;
import java.util.List;

public interface OrderDao {
//    void addOrder(int userId , Date orderDate, List<ShoppingCart> bookList);

    void addOrder(Date orderDate, ShoppingCartSession shoppingCartSession);

    List<Order> getUserOrder(int userId);

    List<Order> getOrders();

    List<OrderItem> getOrderItems(int orderId);
}
