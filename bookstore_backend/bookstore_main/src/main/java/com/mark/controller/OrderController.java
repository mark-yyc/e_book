package com.mark.controller;

import com.mark.dto.ShoppingCart;
import com.mark.entity.BookInfoInCart;
import com.mark.entity.Order;
import com.mark.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    WebApplicationContext applicationContext;

    @RequestMapping("/addOrder")
    public boolean addOrder(@RequestParam("orderDate") Date orderDate) {
        ShoppingCart shoppingCart = applicationContext.getBean(ShoppingCart.class);
        if (shoppingCart.getShoppingCartSize() == 0) {
            return false;
        }
        orderService.addOrder(orderDate, shoppingCart);
        shoppingCart.clear();
        return true;
    }

    @RequestMapping("/addCartItem")
    public boolean addCartItem(@RequestParam("userId")int userId,@RequestParam("bookId")int bookId){
        ShoppingCart shoppingCart =applicationContext.getBean(ShoppingCart.class);
        shoppingCart.setUserId(userId);
        shoppingCart.addCartItem(bookId);
        return true;
    }

    @RequestMapping("/getShoppingCart")
    public ShoppingCart getShoppingCart(){
        return applicationContext.getBean(ShoppingCart.class);

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
