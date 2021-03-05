package com.reins.bookstore.controller;

import com.reins.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/addOrder")
    public void addOrder(@RequestParam("userId")int userId ,@RequestParam("bookList") String bookList){
        orderService.addOrder(userId,bookList);
    }
}
