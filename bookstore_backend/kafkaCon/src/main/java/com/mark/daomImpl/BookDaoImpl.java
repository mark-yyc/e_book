package com.mark.daomImpl;

import com.mark.dao.BookDao;
import com.mark.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void decreaseInventory(int bookId,int amount){
        bookRepository.decreaseInventory(bookId,amount);
    }
}
