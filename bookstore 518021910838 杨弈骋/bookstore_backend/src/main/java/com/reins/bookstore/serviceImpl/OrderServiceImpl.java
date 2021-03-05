package com.reins.bookstore.serviceImpl;

import com.reins.bookstore.dao.BookDao;
import com.reins.bookstore.dao.OrderDao;
import com.reins.bookstore.dao.ShoppingCartDao;
import com.reins.bookstore.entity.*;
import com.reins.bookstore.service.OrderService;
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

    @Override
    public void addOrder(int userId , Date orderDate,List<ShoppingCart>bookList){
//        System.out.println(orderDate);
        orderDao.addOrder(userId,orderDate,bookList);
        shoppingCartDao.clearShoppingCart(userId);
        for (int i=0;i<bookList.size();i++){
            bookDao.decreaseInventory(bookList.get(i).getBookId(),bookList.get(i).getAmount());
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
//            System.out.println(name);
//            System.out.println(price);
//            System.out.println(amount);
            BookInfoInCart one = new BookInfoInCart(bookId,name, price, amount,image);
            result.add(one);
        }
//        for (BookInfoInCart book:result){
//            System.out.println(book.getName());
//            System.out.println(book.getPrice());
//            System.out.println(book.getAmount());
//        }
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
