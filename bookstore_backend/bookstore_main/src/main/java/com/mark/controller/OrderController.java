package com.mark.controller;

import com.google.gson.JsonObject;
import com.mark.BookstoreApplication;
import com.mark.dto.ShoppingCart;
import com.mark.dto.ShoppingCartItem;
import com.mark.entity.Book;
import com.mark.entity.BookInfoInCart;
import com.mark.entity.Order;
import com.mark.service.BookService;
import com.mark.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    WebApplicationContext applicationContext;

    @Autowired
    BookService bookService;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @RequestMapping("/addOrder")
    public boolean addOrder(@RequestParam("orderDate") Date orderDate) {
        ShoppingCart shoppingCart = applicationContext.getBean(ShoppingCart.class);
        shoppingCart.setOrderDate(orderDate);
        if (shoppingCart.getShoppingCartList()==null||shoppingCart.getShoppingCartList().size() == 0) {
            return false;
        }
        kafkaTemplate.send("addOrder", BookstoreApplication.gson.toJson(shoppingCart));
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
    public List<BookInfoInCart> getShoppingCart(){
        List<ShoppingCartItem> shoppingCartItemList=applicationContext.getBean(ShoppingCart.class).getShoppingCartList();
        if (shoppingCartItemList==null||shoppingCartItemList.size()==0){
            return null;
        }
        List<BookInfoInCart>bookInfoInCartList=new ArrayList<>();
        for (ShoppingCartItem shoppingCartItem:shoppingCartItemList){
            Book tmp=bookService.findBookById(shoppingCartItem.getBookId());
            bookInfoInCartList.add(new BookInfoInCart(tmp,shoppingCartItem.getAmount()));
        }
        return bookInfoInCartList;
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

    @RequestMapping("/fwwbTest")
    public String fwwbTest(@RequestParam("itemId")String itemId){
        return "success";
    }
}
