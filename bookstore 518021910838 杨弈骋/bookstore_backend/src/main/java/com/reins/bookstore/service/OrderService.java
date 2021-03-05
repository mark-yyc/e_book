package com.reins.bookstore.service;

import com.reins.bookstore.entity.BookInfoInCart;
import com.reins.bookstore.entity.Order;
import com.reins.bookstore.entity.OrderItem;
import com.reins.bookstore.entity.ShoppingCart;

import java.sql.Date;
import java.util.List;

public interface OrderService {
    void addOrder(int userId , Date orderDate, List<ShoppingCart>bookList);

    void addCartItem(int userId,int bookId);

    List<ShoppingCart> getShoppingCart(int userId);

    List<BookInfoInCart> shoppingCart(int userId);

    List<Order> getUserOrder(int userId);

    List<Order> getOrders();

    List<BookInfoInCart> getOrderItems(int orderId);
}
