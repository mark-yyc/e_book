package com.mark.controller;

import com.mark.dto.BookDetail;
import com.mark.service.BookDetailService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@RestController
public class BookController {
    @RequestMapping("/getBookDetailByName")
    public BookDetail getBookDetailByName(@RequestParam("name") String bookname) throws RemoteException, NotBoundException, MalformedURLException {
        BookDetailService bookDetailService=(BookDetailService) Naming.lookup("rmi://127.0.0.1:1099/bookDetailService");
        return bookDetailService.getBookDetail(bookname);
    }
}
