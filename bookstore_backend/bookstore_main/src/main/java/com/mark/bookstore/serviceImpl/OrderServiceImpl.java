package com.mark.bookstore.serviceImpl;

import com.mark.bookstore.dao.BookDao;
import com.mark.bookstore.dao.OrderDao;
import com.mark.bookstore.dao.ShoppingCartDao;
import com.mark.bookstore.dto.CartItem;
import com.mark.bookstore.dto.ShoppingCartSession;
import com.mark.bookstore.entity.*;
import com.mark.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private BookDao bookDao;

//    @Override
//    public void addOrder(int userId , Date orderDate,List<ShoppingCart>bookList){
//        orderDao.addOrder(userId,orderDate,bookList);
//        shoppingCartDao.clearShoppingCart(userId);
//        for (ShoppingCart shoppingCart : bookList) {
//            bookDao.decreaseInventory(shoppingCart.getBookId(), shoppingCart.getAmount());
//        }
//    }


    @Override
    public void addOrder(Date orderDate, ShoppingCartSession shoppingCartSession) {
        orderDao.addOrder(orderDate, shoppingCartSession);
        List<CartItem> cartItemList = shoppingCartSession.getShoppingCartList();
        for (CartItem cartItem : cartItemList) {
            bookDao.decreaseInventory(cartItem.getBookId(), cartItem.getAmount());
        }
    }

    @Override
    public void addCartItem(int userId,int bookId){
        shoppingCartDao.addCartItem(userId, bookId);
    }

    @Override
    public List<ShoppingCart> getShoppingCart(int userId){
        return shoppingCartDao.getShoppingCart(userId);
    }

    @Override
    public List<BookInfoInCart> shoppingCart(int userId){
        List<BookInfoInCart>result=new ArrayList<>();
        List<ShoppingCart> tmp=shoppingCartDao.getShoppingCart(userId);
        for (ShoppingCart shoppingCart : tmp) {
            int bookId = shoppingCart.getBookId();
            int amount = shoppingCart.getAmount();
            Book oneBook = bookDao.findOne(bookId);
            String name = oneBook.getName();
            String image=oneBook.getImage();
            double price = oneBook.getPrice();
            BookInfoInCart one = new BookInfoInCart(bookId,name, price, amount,image);
            result.add(one);
        }
        return result;
    }

    @Override
    public List<Order> getUserOrder(int userId){
        return orderDao.getUserOrder(userId);
    }

    @Override
    public List<Order> getOrders(){
        return orderDao.getOrders();
    }

    @Override
    public List<BookInfoInCart> getOrderItems(int orderId){
        List<BookInfoInCart>result=new ArrayList<>();
        List<OrderItem>tmp=orderDao.getOrderItems(orderId);
        for (OrderItem orderItem : tmp) {
            int bookId=orderItem.getBookId();
            int amount=orderItem.getAmount();
            Book oneBook = bookDao.findOne(bookId);
            String name = oneBook.getName();
            String image=oneBook.getImage();
            double price = oneBook.getPrice();
            BookInfoInCart one = new BookInfoInCart(bookId,name, price, amount,image);
            result.add(one);
        }
        return result;
    }


}
