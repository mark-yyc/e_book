package com.mark.repository;

import com.mark.entity.Book;
import com.mark.entity.BookImage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookImageRepository extends MongoRepository<BookImage,String> {
    BookImage findFirstByBookId(Integer bookId);
}
