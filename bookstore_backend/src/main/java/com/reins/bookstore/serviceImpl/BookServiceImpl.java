package com.reins.bookstore.serviceImpl;

import com.reins.bookstore.dao.BookDao;
import com.reins.bookstore.dao.OrderDao;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.BookInfoInCart;
import com.reins.bookstore.entity.Order;
import com.reins.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private OrderDao orderDao;

    @Override
    public Book findBookById(Integer id){
        return bookDao.findOne(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookDao.getBooks();
    }

    @Override
    public Book modifyBook(Integer bookId, String name, String isbn,String type, String author, double price, String description, Integer inventory,String image){
        return bookDao.modifyBook(bookId,name,isbn,type,author,price,description,inventory,image);
    }

    @Override
    public void modifyBookState(Integer bookId,Integer state){
        bookDao.modifyBookState(bookId,state);
    }

    @Override
    public void addBook(String isbn,String name,String type,String author,Double price,String description,Integer inventory,String image){
        bookDao.addBook(isbn, name, type, author, price, description, inventory, image);
    }

//    @Override
//    public List<BookInfoInCart> getUserBook(int userId){
//        List<Order> orders=orderDao.getUserOrder(userId);
//        List<BookInfoInCart> bookInfo=new ArrayList<>();
//        for (Order order:orders){
//            List<BookInfoInCart> tmp=orderDao.getOrderItems(order.getOrderId());
//        }
//    }
}
