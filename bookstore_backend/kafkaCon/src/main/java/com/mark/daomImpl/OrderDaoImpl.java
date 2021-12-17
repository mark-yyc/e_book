package com.mark.daomImpl;

import com.mark.dao.OrderDao;
import com.mark.dto.ShoppingCart;
import com.mark.dto.ShoppingCartItem;
import com.mark.entity.Order;
import com.mark.entity.OrderItem;
import com.mark.repository.OrderItemRepository;
import com.mark.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void addOrder(Date orderDate, ShoppingCart shoppingCart) {
        System.out.println("method: "+this.getClass().toString()+" transaction: "+TransactionSynchronizationManager.getCurrentTransactionName());
        System.out.println("addOrder orderDate: "+orderDate.toString());
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

}
