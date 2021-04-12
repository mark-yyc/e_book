package com.mark.daoimpl;

import com.mark.dao.BookDao;
import com.mark.dto.BookDetail;
import com.mark.entity.Book;
import com.mark.entity.BookImage;
import com.mark.repository.BookImageRepository;
import com.mark.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @Override
//    public Book findOne(Integer id) {
//        return bookRepository.getOne(id);
//    }


    @Override
    public Book findOne(Integer id) {
        Book book=bookRepository.getOne(id);
        Optional<BookImage> image=bookImageRepository.findById(id);
        if (image.isPresent()){
            book.setImage(image.get());
        }
        else{
            book.setImage(null);
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
    public BookDetail findFirstByName(String name) {
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
        Optional<BookImage> image=bookImageRepository.findById(book.getBookId());
        if(image.isPresent()){
            bookDetail.setImage(image.get().getIconBase64());
        }
        else {
            bookDetail.setImage(null);
        }
        return bookDetail;
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
