package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.OrderDao;
import com.reins.bookstore.entity.Order;
import com.reins.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void addOrder(int userId ,String bookList){
        Order oneOrder=new Order();
        oneOrder.setUserId(userId);
        oneOrder.setBookList(bookList);
//        List<Order> tmp=orderRepository.getMaxOrderId();
//
//        if (tmp==null){
//            oneOrder.setOrderId(1);
//        }
//        else {
//            int maxid=0;
//            for (Order item:tmp){
//                if(item.getOrderId()>maxid)
//                    maxid=item.getOrderId();
//            }
//            oneOrder.setOrderId(maxid+1);
//        }

        orderRepository.save(oneOrder);
    }
}
