package com.mark.repository;

import com.mark.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {
    @Query(value = "from ShoppingCart where userId = :userId")
    List<ShoppingCart> getShoppingCart(@Param("userId") Integer userId);

    @Query(value = "from ShoppingCart where userId = :userId and bookId = :bookId")
    ShoppingCart getShoppingItem(@Param("userId") Integer userId, @Param("bookId") Integer bookId);

    @Transactional
    @Modifying
    @Query(value="update ShoppingCart set amount=amount+1 where userId = :userId and bookId = :bookId")
    void addmoreItem(@Param("userId") Integer userId, @Param("bookId") Integer bookId);

    @Transactional
    @Modifying
    @Query(value="delete from ShoppingCart where userId = :userId")
    void clearShoppingCart(@Param("userId") Integer userId);
}
