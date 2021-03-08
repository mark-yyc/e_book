package com.reins.bookstore.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartItem implements Serializable {
    private int bookId;
    private int amount;
}
