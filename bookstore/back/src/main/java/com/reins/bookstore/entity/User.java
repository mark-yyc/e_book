package com.reins.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "userId")
public class User {
    private int userId;
    private String username;
    private String password;
    private Integer userType;

    private String name;
    private String nickname;
    private String tel;
    private String address;

    public User(){}

    public User(Integer userId,String username,String password,Integer userType,String name, String nickname,
        String tel, String address) {
        this.userId=userId;
        this.username=username;
        this.password=password;
        this.userType=userType;
        this.name=name;
        this.nickname=nickname;
        this.tel=tel;
        this.address=address;
    }

    //userid
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }
    private void setUserId(Integer id) {
        this.userId = id;
    }

    //password
    @Column(name = "password")
    public String getPassword() {
        return password;
    }
    private void setPassword(String password) {
        this.password = password;
    }

    //name
    @Column(name = "name")
    public String getName() {
        return name;
    }
    private void setName(String name) {
        this.name=name;
    }

    //nickname
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }
    private void setNickname(String nickname) {
        this.nickname = nickname;
    }

    //username
    @Column(name = "username")
    public String getUsername() {
        return username;
    }
    private void setUsername(String username) {
        this.username = username;
    }

    //tel
    @Column(name = "tel")
    public String getTel() {
        return tel;
    }
    private void setTel(String tel) {
        this.tel = tel;
    }

    //address
    @Column(name = "address")
    public String getAddress() {
        return address;
    }
    private void setAddress(String address) {
        this.address = address;
    }

    //userType
    @Column(name = "user_type")
    public Integer getUserType() {
        return userType;
    }
    private void setUserType(Integer userType) {
        this.userType = userType;
    }
}
