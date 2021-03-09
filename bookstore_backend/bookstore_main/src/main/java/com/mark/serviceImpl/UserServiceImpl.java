package com.mark.serviceImpl;

import com.mark.dao.UserDao;
import com.mark.entity.User;
import com.mark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password){
        return userDao.checkUser(username,password);
    }

    @Override
    public List<User> getUsers(){
        return userDao.getUsers();
    }

    @Override
    public void modifyState(int userId,int state){
        userDao.modifyState(userId,state);
    }

    @Override
    public void addUser(String username,String password,String address){
        userDao.addUser(username,password,address);
    }

    @Override
    public User findUser(String username){
        return userDao.findUser(username);
    }
}
