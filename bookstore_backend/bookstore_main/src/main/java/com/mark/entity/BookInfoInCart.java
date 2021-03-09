package com.mark.entity;

public class BookInfoInCart {
    private int bookId;
    private String name;
    private Double price;
    private int amount;
    private String image;

    public BookInfoInCart(int bookId,String name, Double price,int amount,String image){
        this.bookId=bookId;
        this.name=name;
        this.price=price;
        this.amount=amount;
        this.image=image;
    }

    public int getBookId(){
        return bookId;
    }
    public String getName(){
        return name;
    }

    public Double getPrice(){
        return price;
    }

    public int getAmount(){
        return amount;
    }

    public String getImage() {
        return this.image;
    }

}
