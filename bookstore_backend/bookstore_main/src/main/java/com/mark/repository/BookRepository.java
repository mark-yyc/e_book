package com.mark.repository;

import com.mark.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
//    @Query("select b from Book b")
//    List<Book> getBooks();

    @Query(value = "from Book where state = 1")
    List<Book> getBooks();

    Book findFirstByName(String name);

    @Query(value="from Book where bookId in :bookIdList")
    List<Book> findByBookIdList(@Param("bookIdList") List<Integer> bookIdList);

    @Transactional
    @Modifying
    @Query(value="update Book set inventory=inventory-:amount where bookId = :bookId")
    void decreaseInventory(@Param("bookId") Integer bookId,@Param("amount") Integer amount );

    @Transactional
    @Modifying
    @Query(value="update Book set inventory=:inventory ,name=:name,isbn=:isbn,type=:type,author=:author,price=:price,description=:description,inventory=:inventory where bookId = :bookId")
    void modifyBook(@Param("bookId") Integer bookId,@Param("name")String name,@Param("isbn")String isbn,@Param("type")String type,
                    @Param("author")String author,@Param("price")double price,@Param("description")String description,@Param("inventory")Integer inventory);

    @Transactional
    @Modifying
    @Query(value="update Book set state=:state where bookId = :bookId")
    void modifyBookState(@Param("bookId") Integer bookId,@Param("state") Integer state );
}
