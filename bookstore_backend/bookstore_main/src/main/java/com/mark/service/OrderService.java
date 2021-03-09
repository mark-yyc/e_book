package com.mark.service;

import com.mark.dto.ShoppingCartSession;
import com.mark.entity.BookInfoInCart;
import com.mark.entity.Order;
import com.mark.entity.ShoppingCart;

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
