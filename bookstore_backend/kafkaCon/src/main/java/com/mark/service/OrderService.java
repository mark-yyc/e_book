package com.mark.service;

import com.mark.dto.ShoppingCart;

import java.sql.Date;

public interface OrderService {
    void addOrder(ShoppingCart shoppingCart);
}
