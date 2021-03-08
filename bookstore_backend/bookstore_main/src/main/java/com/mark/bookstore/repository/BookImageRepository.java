package com.mark.bookstore.repository;

import com.mark.bookstore.entity.BookImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookImageRepository extends MongoRepository<BookImage,Integer> {

}
