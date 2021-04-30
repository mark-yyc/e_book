package com.mark.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "book")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "bookId")
public class Book {
    private int bookId;
    private String isbn;
    private String name;
    private String type;
    private String author;
    private Double price;
    private String description;
    private Integer inventory;
    private String image;
    private Integer state;

    public Book() {}

    public Book(String isbn,String name,String type,String author,Double price,String description,Integer inventory,Integer state) {
        this.isbn=isbn;
        this.name=name;
        this.type=type;
        this.author=author;
        this.price=price;
        this.description=description;
        this.inventory=inventory;
//        this.image=image;
        this.state=state;
    }
    //id
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    public Integer getBookId() {
        return bookId;
    }
    private void setBookId(Integer id) {
        this.bookId = id;
    }

    //isbn
    public String getIsbn() {
        return isbn;
    }
    private void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    //name
    public String getName() {
        return name;
    }
    private void setName(String name) {
        this.name = name;
    }

    //type
    public String getType() {
        return type;
    }
    private void setType(String type) {
        this.type = type;
    }

    //author
    public String getAuthor() {
        return author;
    }
    private void setAuthor(String author) {
        this.author = author;
    }

    //descrption
    public String getDescription() {
        return description;
    }
    private void setDescription(String description) {
        this.description = description;
    }


    //price
    public Double getPrice() {
        return price;
    }
    private void setPrice(Double price) {
        this.price = price;
    }

    //inventory
    public Integer getInventory() {
        return inventory;
    }
    private void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    //state
    public Integer getState(){return state;}
    public void setState(Integer state){this.state=state;}

    //image
    @Transient
    public String getImage() {
        return image;
    }

    public void setImage(BookImage image) {
        if(image==null){
            this.image=null;
        }else
        this.image = image.getIconBase64();
    }
}
