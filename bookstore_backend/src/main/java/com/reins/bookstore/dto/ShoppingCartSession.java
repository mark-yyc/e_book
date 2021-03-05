package com.reins.bookstore.dto;

import com.reins.bookstore.entity.ShoppingCart;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
@Data
public class ShoppingCartSession implements Serializable {
    private int userId;
    private List<CartItem> shoppingCartList;
    public void addCartItem(int bookId){
        if(shoppingCartList==null){
            shoppingCartList=new ArrayList<>();
        }
        for(CartItem cartItem : shoppingCartList){
            if(cartItem.getBookId()==bookId){
                cartItem.setAmount(cartItem.getAmount()+1);
                return;
            }
        }
        CartItem cartItem=new CartItem();
        cartItem.setBookId(bookId);
        cartItem.setAmount(1);
        shoppingCartList.add(cartItem);
    }
}
