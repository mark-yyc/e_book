package com.reins.bookstore.controller;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/getBooks")
//    public List<Book> getBooks(@RequestBody Map<String, String> params) {
//        return bookService.getBooks();
//    }

    public List<Book> getBooks() {
        return bookService.getBooks();
    }
//    @RequestMapping("/getBook/{id}")
//    public Book getBook(@PathVariable("id") Integer id){
//        return bookService.findBookById(id);
//    }

    @RequestMapping("/getBook")
    public Book getBook(@RequestParam("id") Integer id){
        return bookService.findBookById(id);
    }
//    @GetMapping(value = "/findBook/{id}")
//    public Book findEvent(@PathVariable("id") Integer id) {
//        System.out.println("Searching Event: " + id);
//        return bookService.findBookById(id);
//    }
}

