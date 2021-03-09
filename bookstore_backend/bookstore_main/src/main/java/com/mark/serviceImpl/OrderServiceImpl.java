package com.mark.serviceImpl;

import com.mark.dao.BookDao;
import com.mark.dao.OrderDao;
import com.mark.dto.ShoppingCartItem;
import com.mark.dto.ShoppingCart;
import com.mark.service.OrderService;
import com.mark.entity.*;
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
    public void addOrder(Date orderDate, ShoppingCart shoppingCart) {
        orderDao.addOrder(orderDate, shoppingCart);
        List<ShoppingCartItem> shoppingCartItemList = shoppingCart.getShoppingCartList();
        for (ShoppingCartItem shoppingCartItem : shoppingCartItemList) {
            bookDao.decreaseInventory(shoppingCartItem.getBookId(), shoppingCartItem.getAmount());
        }
    }

    @Override
    public List<Order> getUserOrder(int userId){
        return orderDao.getUserOrder(userId);
    }

    @Override
    public List<Order> getOrders(){
        return orderDao.getOrders();
    }

    @Override
    public List<BookInfoInCart> getOrderItems(int orderId){
        List<BookInfoInCart>result=new ArrayList<>();
        List<OrderItem>tmp=orderDao.getOrderItems(orderId);
        for (OrderItem orderItem : tmp) {
            int bookId=orderItem.getBookId();
            int amount=orderItem.getAmount();
            Book oneBook = bookDao.findOne(bookId);
            String name = oneBook.getName();
            String image=oneBook.getImage();
            double price = oneBook.getPrice();
            BookInfoInCart one = new BookInfoInCart(bookId,name, price, amount,image);
            result.add(one);
        }
        return result;
    }


}
