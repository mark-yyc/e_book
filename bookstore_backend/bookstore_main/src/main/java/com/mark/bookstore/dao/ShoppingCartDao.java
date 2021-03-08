package com.mark.bookstore.dao;

import com.mark.bookstore.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao {
    void addCartItem(int userId,int bookId);

    void clearShoppingCart(int userId);

    List<ShoppingCart> getShoppingCart(int userId);
}
