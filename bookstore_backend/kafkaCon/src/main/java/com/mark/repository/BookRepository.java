package com.mark.repository;
import com.mark.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface BookRepository extends JpaRepository<Book,Integer> {

    @Transactional
    @Modifying
    @Query(value="update Book set inventory=inventory-:amount where bookId = :bookId")
    void decreaseInventory(@Param("bookId") Integer bookId,@Param("amount") Integer amount );
}
