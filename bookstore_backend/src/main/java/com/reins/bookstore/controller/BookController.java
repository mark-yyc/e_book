package com.reins.bookstore.controller;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/getBooks")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @RequestMapping("/getBook")
    public Book getBook(@RequestParam("id") Integer id){
        return bookService.findBookById(id);
    }

    @RequestMapping("/modifyBook")
    public Book modifyBook(@RequestParam("id") Integer id,@RequestParam("name")String name,@RequestParam("isbn")String isbn,@RequestParam("type")String type,
                    @RequestParam("author")String author,@RequestParam("price")double price,@RequestParam("description")String description,@RequestParam("inventory")Integer inventory,@RequestParam("image")String image){
        return bookService.modifyBook(id,name,isbn,type,author,price,description,inventory,image);
    }

    @RequestMapping("/modifyBookState")
    public void modifyBookState(@RequestParam("id") Integer id,@RequestParam("state")Integer state){
        bookService.modifyBookState(id,state);
    }

    @RequestMapping("/addBook")
    public boolean addBook(@RequestParam("name")String name,@RequestParam("isbn")String isbn,@RequestParam("type")String type,
                    @RequestParam("author")String author,@RequestParam("price")double price,@RequestParam("description")String description,@RequestParam("inventory")Integer inventory,@RequestParam("image")String image){
        bookService.addBook(isbn,name,type,author,price,description,inventory,image);
        return true;
    }
}

