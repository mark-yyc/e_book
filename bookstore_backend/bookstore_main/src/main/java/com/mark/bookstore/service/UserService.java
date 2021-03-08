package com.mark.bookstore.service;

import com.mark.bookstore.entity.User;

import java.util.List;

public interface UserService {
    User checkUser(String username, String password);

    List<User> getUsers();

    void modifyState(int userId,int state);

    void addUser(String username,String password,String address);

    User findUser(String username);
}
