package com.mark.serviceImpl;

import com.mark.dao.BookDao;
import com.mark.dto.BookDetail;
import com.mark.entity.Book;
import com.mark.service.BookDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@Service
public class BookDetailServiceImpl extends UnicastRemoteObject implements BookDetailService {

    @Autowired
    private BookDao bookDao;

    public BookDetailServiceImpl() throws RemoteException {
    }

    @Override
    public BookDetail getBookDetail(String bookName) throws RemoteException {
        return bookDao.findFirstByName(bookName);
    }
}
