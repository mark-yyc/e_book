package com.mark.dao;

import com.mark.dto.ShoppingCartSession;
import com.mark.entity.Order;
import com.mark.entity.OrderItem;

import java.sql.Date;
import java.util.List;

public interface OrderDao {
//    void addOrder(int userId , Date orderDate, List<ShoppingCart> bookList);

    void addOrder(Date orderDate, ShoppingCartSession shoppingCartSession);

    List<Order> getUserOrder(int userId);

    List<Order> getOrders();

    List<OrderItem> getOrderItems(int orderId);
}
