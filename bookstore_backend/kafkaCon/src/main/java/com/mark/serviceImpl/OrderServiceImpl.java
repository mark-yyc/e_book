package com.mark.serviceImpl;

import com.mark.dao.BookDao;
import com.mark.dao.OrderDao;
import com.mark.dto.ShoppingCart;
import com.mark.dto.ShoppingCartItem;
import com.mark.entity.Order;
import com.mark.entity.OrderItem;
import com.mark.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public void addOrder(ShoppingCart shoppingCart) {
        orderDao.addOrder(shoppingCart.getOrderDate(), shoppingCart);
        List<ShoppingCartItem> shoppingCartItemList = shoppingCart.getShoppingCartList();
        for (ShoppingCartItem shoppingCartItem : shoppingCartItemList) {
            bookDao.decreaseInventory(shoppingCartItem.getBookId(), shoppingCartItem.getAmount());
        }
    }


}
