package com.mark.controller;

import com.mark.entity.Book;
import com.mark.service.BookService;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
                    @RequestParam("author")String author,@RequestParam("price")double price,@RequestParam(name="description",required=false)String description,
                           @RequestParam("inventory")Integer inventory,@RequestParam(name="image",required=false)String image){
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

    @RequestMapping("/findByRelatedLabel")
    public List<Book> findByRelatedLabel(@RequestParam("label")String labelName){
        return bookService.findByRelatedLabel(labelName);
    }

    @RequestMapping(value = "/book",method = RequestMethod.GET)
    public List<Book> searchWithTextIndex(@RequestParam("search")String search) throws IOException, ParseException {
        return bookService.searchWithTextIndex(search);
    }
}

