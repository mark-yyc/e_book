package com.mark.controller;

import com.mark.BookstoreApplication;
import com.mark.entity.User;
import com.mark.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/checkUser")
    public User checkUser(@RequestParam("username") String username, @RequestParam("password") String password){
        User user=userService.checkUser(username, password);
        if(user!=null){
            BookstoreApplication.atomicAmount.increment();
        }
        return user;
    }

    @RequestMapping("/getViewAmount")
    public int getViewAmount(){
        return BookstoreApplication.atomicAmount.getValue();
    }

    @RequestMapping("/getUsers")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @RequestMapping("/modifyState")
    public List<User> modifyState(@RequestParam("userId")int userId, @RequestParam("state")int state){
        userService.modifyState(userId,state);
        return userService.getUsers();
    }

    @RequestMapping("/addUser")
    public boolean addUser(@RequestParam("username")String username, @RequestParam("password")String password,@RequestParam("address") String address){
        userService.addUser(username,password,address);
        return true;
    }

    @RequestMapping("/findUser")
    public User findUser(@RequestParam("username")String username){
        return userService.findUser(username);
    }
}
