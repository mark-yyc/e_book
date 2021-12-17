package com.mark.daoimpl;

import com.mark.dao.OrderDao;
import com.mark.dto.ShoppingCartItem;
import com.mark.dto.ShoppingCart;
import com.mark.entity.Order;
import com.mark.entity.OrderItem;
import com.mark.repository.OrderItemRepository;
import com.mark.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Override
    public List<Order> getUserOrder(int userId){
        return orderRepository.getUserOrder(userId);
    }

    @Override
    public List<Order> getOrders(){
        return orderRepository.getOrders();
    }

    @Override
    public List<OrderItem> getOrderItems(int orderId){
        return orderItemRepository.getOrderItems(orderId);
    }

}
