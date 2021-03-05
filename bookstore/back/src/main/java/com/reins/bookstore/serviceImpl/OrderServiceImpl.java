package com.reins.bookstore.serviceImpl;

import com.reins.bookstore.dao.OrderDao;
import com.reins.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public void addOrder(int userId ,String bookList){
        orderDao.addOrder(userId,bookList);
    }

}
