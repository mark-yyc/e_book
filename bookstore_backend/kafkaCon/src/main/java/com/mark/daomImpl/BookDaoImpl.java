package com.mark.daomImpl;

import com.mark.dao.BookDao;
import com.mark.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void decreaseInventory(int bookId,int amount){
        System.out.println("method: "+this.getClass().toString()+" transaction: "+TransactionSynchronizationManager.getCurrentTransactionName());
        System.out.println("decrease book inventory: bookId: "+bookId+" amount: "+amount);
        bookRepository.decreaseInventory(bookId,amount);
    }
}
