package com.mark.service;

import com.mark.entity.Book;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.List;

public interface BookService {
    Book findBookById(Integer id);

    List<Book> getBooks();

    void modifyBookState(Integer bookId,Integer state);

    void addBook(String isbn,String name,String type,String author,Double price,String description,Integer inventory,String image);

    Book modifyBook(Integer bookId, String name, String isbn,String type, String author, double price, String description, Integer inventory,String image);

//    List<BookInfoInCart> getUserBook(int userId);
    List<Book> findByRelatedLabel(String labelName);

    List<Book> searchWithTextIndex(String search) throws IOException, ParseException;
}
