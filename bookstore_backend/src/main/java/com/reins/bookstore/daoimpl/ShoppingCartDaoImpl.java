package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.ShoppingCartDao;
import com.reins.bookstore.entity.ShoppingCart;
import com.reins.bookstore.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public void addCartItem(int userId,int bookId){
        ShoppingCart cartItem=shoppingCartRepository.getShoppingItem(userId,bookId);
        if(cartItem==null){
            cartItem=new ShoppingCart();
            cartItem.setUserId(userId);
            cartItem.setBookId(bookId);
            cartItem.setAmount(1);
            shoppingCartRepository.save(cartItem);
        }
        else{
            shoppingCartRepository.addmoreItem(userId,bookId);
        }
    }

    @Override
    public List<ShoppingCart> getShoppingCart(int userId){
        return shoppingCartRepository.getShoppingCart(userId);
    }

    @Override
    public void clearShoppingCart(int userId){
        shoppingCartRepository.clearShoppingCart(userId);
    }
}
