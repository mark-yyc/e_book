package com.reins.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="shopping_cart")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "cartItemId")
public class ShoppingCart {
    private int cartItemId;
    private int userId;
    private int bookId;
    private int amount;

    //cartItemId
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name="cart_item_id")
    public int getCartItemId(){
        return cartItemId;
    }

    public void setCartItemId(int cartItemId){
        this.cartItemId=cartItemId;
    }

    //userId
    @Column(name="user_id")
    public int getUserId(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId=userId;
    }

    //bookId
    @Column(name="id")
    public int getBookId(){
        return bookId;
    }

    public void setBookId(int bookId){
        this.bookId=bookId;
    }

    //amount
    @Column(name="amount")
    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount=amount;
    }
}
