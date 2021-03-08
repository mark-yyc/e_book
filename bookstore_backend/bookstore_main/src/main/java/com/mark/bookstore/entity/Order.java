package com.mark.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "orderId")
public class Order {

    private int orderId;
    private int userId;
    private Date orderDate;

    public Order(){};


    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int id) {
        this.orderId = id;
    }

    @Column(name="user_id")
    public int getUserId() {
        return userId;
    }
    public void setUserId(int id) {
        this.userId = id;
    }

    @Column(name="order_date")
    public Date getOrderDate(){return this.orderDate;}
    public void setOrderDate(Date orderDate){this.orderDate=orderDate;}
}
