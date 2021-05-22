package com.mark.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "book")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "bookId")
@Data
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
    public void setBookId(Integer id) {
        this.bookId = id;
    }

    //isbn
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    //name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //type
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    //author
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    //descrption
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    //price
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    //inventory
    public Integer getInventory() {
        return inventory;
    }
    public void setInventory(Integer inventory) {
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
