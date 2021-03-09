package com.mark.controller;

import com.mark.KafkaConApplication;
import com.mark.dto.ShoppingCart;
import com.mark.service.OrderService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer{
    @Autowired
    private OrderService orderService;

    @KafkaListener(topics={"addOrder"})
    public void addOrder(ConsumerRecord<?,?>record){
        System.out.println(record.value());
        ShoppingCart shoppingCart=KafkaConApplication.gson.fromJson(record.value().toString(), ShoppingCart.class);
        orderService.addOrder(shoppingCart);
    }
}
