package com.mark.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookDetail implements Serializable {
    private int bookId;
    private String isbn;
    private String name;
    private String type;
    private String author;
    private Double price;
    private String description;
    private String image;
}
