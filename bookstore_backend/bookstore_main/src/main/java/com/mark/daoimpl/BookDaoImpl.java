package com.mark.daoimpl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mark.BookstoreApplication;
import com.mark.dao.BookDao;
import com.mark.dto.BookDetail;
import com.mark.entity.Book;
import com.mark.entity.BookImage;
import com.mark.repository.BookImageRepository;
import com.mark.repository.BookRepository;
import com.mark.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookImageRepository bookImageRepository;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Book findOne(Integer id) {
        Book book = bookRepository.getOne(id);
//        book.setImage(bookImageRepository.findFirstByBookId(id));
        return book;
    }

    @Override
    public List<Book> getBooks() {
        List<Book> bookList= bookRepository.getBooks();
        for (Book book:bookList){
            book.setImage(bookImageRepository.findFirstByBookId(book.getBookId()));
        }
        return bookList;
    }

    @Override
    public List<Book> findByBookIdList(List<Integer> bookIdList) {
        List<Book> bookList= bookRepository.findByBookIdList(bookIdList);
//        for (Book book:bookList){
//            book.setImage(bookImageRepository.findFirstByBookId(book.getBookId()));
//        }
        return bookList;
    }

    @Override
    public BookDetail findFirstByName(String name) {
        final String key="BookDetail:"+name;
        Object object=redisUtil.get(key);
        if(object==null){
            System.out.println("findFirstByName "+name+" not in cache");
            Book book=bookRepository.findFirstByName(name);
            if (book==null)return null;
            BookDetail bookDetail=new BookDetail();
            bookDetail.setBookId(book.getBookId());
            bookDetail.setName(book.getName());
            bookDetail.setAuthor(book.getAuthor());
            bookDetail.setDescription(book.getDescription());
            bookDetail.setIsbn(book.getIsbn());
            bookDetail.setPrice(book.getPrice());
            bookDetail.setType(book.getType());
            book.setImage(bookImageRepository.findFirstByBookId(book.getBookId()));
            redisUtil.set(key, bookDetail,10);
            return bookDetail;
        }
        else{
            System.out.println("findFirstByName "+name+" found in cache");
            return BookstoreApplication.gson.fromJson(BookstoreApplication.gson.toJson(object),BookDetail.class);
        }
    }

    @Override
    public void decreaseInventory(int bookId,int amount){
        bookRepository.decreaseInventory(bookId,amount);
    }

    @Override
    public Book modifyBook(Integer bookId, String name, String isbn,String type, String author, double price, String description, Integer inventory,String image){
        bookRepository.modifyBook(bookId,name,isbn,type,author,price,description,inventory);
        String key="BookDetail:"+name;
        Object object=redisUtil.get(key);
        if(object!=null){
            System.out.println("modifyBook delete cache "+key);
            redisUtil.del(key);
        }
        else {
            System.out.println("modifyBook " + key + " not in cache");
        }
        if(image!=null) {
            BookImage bookImage = new BookImage(bookId, image);
            bookImageRepository.save(bookImage);
        }
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
