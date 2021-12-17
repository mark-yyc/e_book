package com.mark.dto;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Component
@Scope("session")
@Data
public class ShoppingCart implements Serializable {
    private int userId;
    private Date orderDate;
    private List<ShoppingCartItem> shoppingCartList;

    public void addCartItem(int bookId){
        if(shoppingCartList==null){
            shoppingCartList=new ArrayList<>();
        }
        for(ShoppingCartItem shoppingCartItem : shoppingCartList){
            if (shoppingCartItem.getBookId() == bookId) {
                shoppingCartItem.setAmount(shoppingCartItem.getAmount() + 1);
                return;
            }
        }
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setBookId(bookId);
        shoppingCartItem.setAmount(1);
        shoppingCartList.add(shoppingCartItem);
    }

    public void clear() {
        shoppingCartList.clear();
    }
}
