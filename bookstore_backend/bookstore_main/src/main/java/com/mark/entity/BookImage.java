package com.mark.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "image")
public class BookImage {
    @Id
    private String imageId;
    private int bookId;
    private String iconBase64;

    public BookImage(int bookId, String iconBase64) {
        this.bookId = bookId;
        this.iconBase64 = iconBase64;
    }
}
