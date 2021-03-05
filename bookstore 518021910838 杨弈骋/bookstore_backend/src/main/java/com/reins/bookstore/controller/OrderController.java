package com.reins.bookstore.controller;

import com.reins.bookstore.entity.BookInfoInCart;
import com.reins.bookstore.entity.Order;
import com.reins.bookstore.entity.OrderItem;
import com.reins.bookstore.entity.ShoppingCart;
import com.reins.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/addOrder")
    public boolean addOrder(@RequestParam("userId")int userId, @RequestParam("orderDate")Date orderDate){
//        System.out.println(orderDate);
        List<ShoppingCart> cartContent=orderService.getShoppingCart(userId);
        orderService.addOrder(userId,orderDate,cartContent);
        return true;
    }

    @RequestMapping("/addCartItem")
    public boolean addCartItem(@RequestParam("userId")int userId,@RequestParam("bookId")int bookId){
        orderService.addCartItem(userId, bookId);
        return true;
    }

    @RequestMapping("/getShoppingCart")
    public List<BookInfoInCart> getShoppingCart(@RequestParam("userId")int userId){
        return orderService.shoppingCart(userId);
    }

    @RequestMapping("/getUserOrder")
    public List<Order> getUserOrder(@RequestParam("userId")int userId){
        return orderService.getUserOrder(userId);
    }

    @RequestMapping("/getOrders")
    public List<Order> getOrders(){
        return orderService.getOrders();
    }

    @RequestMapping("/getOrderItem")
    public List<BookInfoInCart> getOrderItem(@RequestParam("orderId") int orderId){
        return orderService.getOrderItems(orderId);
    }
}
