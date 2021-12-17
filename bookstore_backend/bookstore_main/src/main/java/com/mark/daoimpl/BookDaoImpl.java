package com.mark.daoimpl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@EnableScheduling
public class BookDaoImpl implements BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookImageRepository bookImageRepository;

    @Autowired
    private RedisUtil redisUtil;

    @PostConstruct
    public void initBookCache(){
        redisUtil.del("Book");
        List<Book> bookList= bookRepository.findAll();
        for(Book book:bookList){
//            System.out.println(book.toString());
            redisUtil.push("Book",book);
        }
    }

    @Override
    public Book findOne(Integer id) {
        Object object=redisUtil.getWithIndex("Book",id-1);
        Book book= BookstoreApplication.gson.fromJson(BookstoreApplication.gson.toJson(object),Book.class);
//        book.setImage(bookImageRepository.findFirstByBookId(id));
        return book;
    }

    @Override
    public List<Book> getBooks() {
        List<Object> objectList=redisUtil.getList("Book",0,-1);
        List<Book>bookList=BookstoreApplication.gson.
                fromJson(BookstoreApplication.gson.toJson(objectList),new TypeToken<List<Book>>(){}.getType());
        List<Book> result=new ArrayList<>();
        for(Book book :bookList){
            book.setImage(bookImageRepository.findFirstByBookId(book.getBookId()));
            result.add(book);
        }
        return result;
    }

    @Override
    public List<Book> findByBookIdList(List<Integer> bookIdList) {
        List<Book> bookList=new ArrayList<>();
        for(Integer integer:bookIdList){
            if(findOne(integer)==null)continue;
            bookList.add(findOne(integer));
        }
        return bookList;
    }

    @Override
    public BookDetail findFirstByName(String name) {
        List<Object> objectList=redisUtil.getList("Book",0,-1);
        List<Book>bookList=BookstoreApplication.gson.
                fromJson(BookstoreApplication.gson.toJson(objectList),new TypeToken<List<Book>>(){}.getType());
        for(Book book:bookList){
            if(book.getName().equals(name)){
                BookDetail bookDetail=new BookDetail();
                bookDetail.setBookId(book.getBookId());
                bookDetail.setName(book.getName());
                bookDetail.setAuthor(book.getAuthor());
                bookDetail.setDescription(book.getDescription());
                bookDetail.setIsbn(book.getIsbn());
                bookDetail.setPrice(book.getPrice());
                bookDetail.setType(book.getType());
                return bookDetail;
            }
        }
        return null;
    }

    @Override
    public void decreaseInventory(int bookId,int amount){
        Object object=redisUtil.getWithIndex("Book",bookId-1);
        Book book=BookstoreApplication.gson.fromJson(BookstoreApplication.gson.toJson(object),Book.class);
        book.setInventory(book.getInventory()-amount);
        redisUtil.setWithIndex("Book",book.getBookId()-1,book);
    }

    @Override
    public Book modifyBook(Integer bookId, String name, String isbn,String type, String author, double price, String description, Integer inventory,String image){
        Object object=redisUtil.getWithIndex("Book",bookId-1);
        Book book=BookstoreApplication.gson.fromJson(BookstoreApplication.gson.toJson(object),Book.class);
        book.setName(name);
        book.setIsbn(isbn);
        book.setType(type);
        book.setAuthor(author);
        book.setPrice(price);
        book.setDescription(description);
        book.setInventory(inventory);
        redisUtil.setWithIndex("Book",bookId-1,book);
        if(image!=null) {
            BookImage bookImage=new BookImage(bookId,image);
            book.setImage(bookImage);
            bookImageRepository.save(bookImage);
            return book;
        }
        book.setImage(bookImageRepository.findFirstByBookId(book.getBookId()));
        return book;
    }

    @Override
    public void addBook(String isbn,String name,String type,String author,Double price,String description,Integer inventory,String image){
        Book book=new Book(isbn,name,type,author,price, description,inventory,1);
        int bookId=bookRepository.save(book).getBookId();
        redisUtil.setWithIndex("Book",bookId-1,book);
        BookImage bookImage=new BookImage(bookId,image);
        bookImageRepository.save(bookImage);
    }

    @Override
    public void modifyBookState(Integer bookId,Integer state){
        Object object=redisUtil.getWithIndex("Book",bookId-1);
        Book book=BookstoreApplication.gson.fromJson(BookstoreApplication.gson.toJson(object),Book.class);
        book.setState(state);
        redisUtil.setWithIndex("Book",bookId-1,book);
    }

    @Scheduled(fixedDelay = 30*1000)
    void writeInBook(){
        List<Object> objectList=redisUtil.getList("Book",0,-1);
        List<Book>bookList=BookstoreApplication.gson.
                fromJson(BookstoreApplication.gson.toJson(objectList),new TypeToken<List<Book>>(){}.getType());
        for(Book book:bookList){
            bookRepository.save(book);
        }
    }
}
