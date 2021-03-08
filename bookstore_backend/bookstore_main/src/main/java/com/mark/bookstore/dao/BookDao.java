package com.mark.bookstore.dao;

import com.mark.bookstore.entity.Book;

import java.util.List;

public interface BookDao {
    Book findOne(Integer id);

    List<Book> getBooks();

    void decreaseInventory(int bookId,int amount);

    void addBook(String isbn,String name,String type,String author,Double price,String description,Integer inventory,String image);

    void modifyBookState(Integer bookId,Integer state);

    Book modifyBook(Integer bookId, String name, String isbn,String type, String author, double price, String description, Integer inventory,String image);
}
