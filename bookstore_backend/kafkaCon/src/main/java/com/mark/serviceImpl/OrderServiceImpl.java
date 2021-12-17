package com.mark.serviceImpl;

import com.mark.dao.BookDao;
import com.mark.dao.OrderDao;
import com.mark.dto.ShoppingCart;
import com.mark.dto.ShoppingCartItem;
import com.mark.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private BookDao bookDao;

    @Transactional(propagation =Propagation.REQUIRED)
    @Override
    public void addOrder(ShoppingCart shoppingCart) {
        System.out.println("method: "+this.getClass().toString()+" transaction: "+TransactionSynchronizationManager.getCurrentTransactionName());
        orderDao.addOrder(shoppingCart.getOrderDate(), shoppingCart);
        List<ShoppingCartItem> shoppingCartItemList = shoppingCart.getShoppingCartList();
        for (ShoppingCartItem shoppingCartItem : shoppingCartItemList) {
//            try{
            bookDao.decreaseInventory(shoppingCartItem.getBookId(), shoppingCartItem.getAmount());
//            }
//            catch (IllegalTransactionStateException e){
//                System.out.println("No existing transaction found for transaction marked with propagation 'mandatory'");
//                e.printStackTrace();
//            }
        }
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }


}
