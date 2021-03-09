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

//    @Override
//    public void addOrder(int userId , Date orderDate, List<ShoppingCart> bookList){
//
//        Order oneOrder=new Order();
//        oneOrder.setUserId(userId);
//        oneOrder.setOrderDate(orderDate);
////        System.out.println(oneOrder.getOrderDate());
//        Order tmp=orderRepository.save(oneOrder);
//        int  OrderId=tmp.getOrderId();
//        for (int i=0;i<bookList.size();i++){
//            OrderItem oneOrderItem=new OrderItem();
//            oneOrderItem.setOrderId(OrderId);
//            oneOrderItem.setBookId(bookList.get(i).getBookId());
//            oneOrderItem.setAmount(bookList.get(i).getAmount());
//            orderItemRepository.save(oneOrderItem);
//        }
//    }

    @Override
    public void addOrder(Date orderDate, ShoppingCart shoppingCart) {
        Order oneOrder = new Order();
        oneOrder.setUserId(shoppingCart.getUserId());
        oneOrder.setOrderDate(orderDate);
        Order tmp = orderRepository.save(oneOrder);
        int OrderId = tmp.getOrderId();
        List<ShoppingCartItem> shoppingCartItemList = shoppingCart.getShoppingCartList();
        for (ShoppingCartItem shoppingCartItem : shoppingCartItemList) {
            OrderItem oneOrderItem = new OrderItem();
            oneOrderItem.setOrderId(OrderId);
            oneOrderItem.setBookId(shoppingCartItem.getBookId());
            oneOrderItem.setAmount(shoppingCartItem.getAmount());
            orderItemRepository.save(oneOrderItem);
        }
    }

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
