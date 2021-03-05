package com.reins.bookstore.dao;

import com.reins.bookstore.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao {
    void addCartItem(int userId,int bookId);

    void clearShoppingCart(int userId);

    List<ShoppingCart> getShoppingCart(int userId);
}
