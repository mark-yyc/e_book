package com.reins.bookstore.dao;

import com.reins.bookstore.entity.User;

import java.util.List;

public interface UserDao {
     User checkUser(String username, String password);

     List<User> getUsers();
}
