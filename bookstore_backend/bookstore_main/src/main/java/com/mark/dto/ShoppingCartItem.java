package com.mark.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ShoppingCartItem implements Serializable {
    private int bookId;
    private int amount;
}
