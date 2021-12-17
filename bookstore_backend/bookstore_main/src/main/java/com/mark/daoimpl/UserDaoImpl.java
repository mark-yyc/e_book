package com.mark.daoimpl;

import com.mark.dao.UserDao;
import com.mark.entity.User;
import com.mark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserRepository userRepository;

    @Override
    public User checkUser(String username, String password){
        return userRepository.checkUser(username,password);
    }

    @Override
    public List<User> getUsers(){
        return userRepository.getUsers();
    }

    @Override
    public void modifyState(int userId,int state){
        userRepository.modifyState(userId,state);
    }

    @Override
    public void addUser(String username,String password,String address){
        User oneUser=new User();
        oneUser.setUsername(username);
        oneUser.setPassword(password);
        oneUser.setAddress(address);
        oneUser.setUserType(0);
        oneUser.setState(1);
        userRepository.save(oneUser);
    }

    @Override
    public User findUser(String username){
        return userRepository.findUser(username);
    }
}
