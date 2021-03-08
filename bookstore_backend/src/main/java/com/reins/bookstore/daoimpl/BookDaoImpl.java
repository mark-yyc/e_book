package com.reins.bookstore.daoimpl;

import com.reins.bookstore.dao.BookDao;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.BookImage;
import com.reins.bookstore.repository.BookImageRepository;
import com.reins.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookImageRepository bookImageRepository;

//    @Override
//    public Book findOne(Integer id) {
//        return bookRepository.getOne(id);
//    }


    @Override
    public Book findOne(Integer id) {
        Book book=bookRepository.getOne(id);
        Optional<BookImage> image=bookImageRepository.findById(id);
        if (image.isPresent()){
            //System.out.println("Not Null " + id);
            book.setImage(image.get());
        }
        else{
            book.setImage(null);
            //System.out.println("It's Null");
        }
        return book;
    }

    @Override
    public List<Book> getBooks() {
        List<Book> bookList= bookRepository.getBooks();
        for (Book book:bookList){
            Optional<BookImage> image=bookImageRepository.findById(book.getBookId());
            if (image.isPresent()){
                book.setImage(image.get());
            }
            else{
                book.setImage(null);
            }
        }
        return bookList;
    }

    @Override
    public void decreaseInventory(int bookId,int amount){
        bookRepository.decreaseInventory(bookId,amount);
    }

    @Override
    public Book modifyBook(Integer bookId, String name, String isbn,String type, String author, double price, String description, Integer inventory,String image){
        bookRepository.modifyBook(bookId,name,isbn,type,author,price,description,inventory);
        BookImage bookImage=new BookImage(bookId,image);
        bookImageRepository.save(bookImage);
        return findOne(bookId);
    }

    @Override
    public void addBook(String isbn,String name,String type,String author,Double price,String description,Integer inventory,String image){
        Book book=new Book(isbn,name,type,author,price, description,inventory,1);
        int bookId=bookRepository.save(book).getBookId();
        BookImage bookImage=new BookImage(bookId,image);
        bookImageRepository.save(bookImage);
    }

    @Override
    public void modifyBookState(Integer bookId,Integer state){
        bookRepository.modifyBookState(bookId,state);
    }
}
