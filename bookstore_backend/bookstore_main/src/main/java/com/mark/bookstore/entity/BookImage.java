package com.mark.bookstore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "image")
public class BookImage {
    @Id
    private int id;

    private String iconBase64;

    public BookImage(int id, String iconBase64) {
        this.id = id;
        this.iconBase64 = iconBase64;
    }

    public String getIconBase64() {
        return this.iconBase64;
    }

    public void setIconBase64(String iconBase64) {
        this.iconBase64 = iconBase64;
    }
}
