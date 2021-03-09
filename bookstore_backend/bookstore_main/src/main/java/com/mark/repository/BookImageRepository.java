package com.mark.repository;

import com.mark.entity.BookImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookImageRepository extends MongoRepository<BookImage,Integer> {

}
