package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.OrderDao;
import com.reins.bookstore.dto.CartItem;
import com.reins.bookstore.dto.ShoppingCartSession;
import com.reins.bookstore.entity.Order;
import com.reins.bookstore.entity.OrderItem;
import com.reins.bookstore.repository.OrderItemRepository;
import com.reins.bookstore.repository.OrderRepository;
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
    public void addOrder(Date orderDate, ShoppingCartSession shoppingCartSession) {
        Order oneOrder = new Order();
        oneOrder.setUserId(shoppingCartSession.getUserId());
        oneOrder.setOrderDate(orderDate);
        Order tmp = orderRepository.save(oneOrder);
        int OrderId = tmp.getOrderId();
        List<CartItem> cartItemList = shoppingCartSession.getShoppingCartList();
        for (int i = 0; i < cartItemList.size(); i++) {
            OrderItem oneOrderItem = new OrderItem();
            oneOrderItem.setOrderId(OrderId);
            oneOrderItem.setBookId(cartItemList.get(i).getBookId());
            oneOrderItem.setAmount(cartItemList.get(i).getAmount());
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
