package com.mark.bookstore.service;

import com.mark.bookstore.dto.ShoppingCartSession;
import com.mark.bookstore.entity.BookInfoInCart;
import com.mark.bookstore.entity.Order;
import com.mark.bookstore.entity.ShoppingCart;

import java.sql.Date;
import java.util.List;

public interface OrderService {
//    void addOrder(int userId , Date orderDate, List<ShoppingCart>bookList);

    void addOrder(Date orderDate, ShoppingCartSession shoppingCartSession);

    void addCartItem(int userId, int bookId);

    List<ShoppingCart> getShoppingCart(int userId);

    List<BookInfoInCart> shoppingCart(int userId);

    List<Order> getUserOrder(int userId);

    List<Order> getOrders();

    List<BookInfoInCart> getOrderItems(int orderId);
}
