package com.mark.service;

import com.mark.dto.BookDetail;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BookDetailService extends Remote {
    BookDetail getBookDetail(String bookName) throws RemoteException;
}
