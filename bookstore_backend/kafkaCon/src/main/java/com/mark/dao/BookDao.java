package com.mark.dao;

public interface BookDao {
    void decreaseInventory(int bookId,int amount);
}
