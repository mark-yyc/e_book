package com.reins.bookstore.service;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.BookInfoInCart;

import java.util.List;

public interface BookService {
    Book findBookById(Integer id);

    List<Book> getBooks();

    void modifyBookState(Integer bookId,Integer state);

    void addBook(String isbn,String name,String type,String author,Double price,String description,Integer inventory,String image);

    Book modifyBook(Integer bookId, String name, String isbn,String type, String author, double price, String description, Integer inventory,String image);

//    List<BookInfoInCart> getUserBook(int userId);
}
