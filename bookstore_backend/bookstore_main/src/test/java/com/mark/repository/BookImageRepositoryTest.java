package com.mark.repository;

import com.mark.entity.BookImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookImageRepositoryTest {
    @Autowired
    BookImageRepository bookImageRepository;

    @Test
    void findById() {
        System.out.println(bookImageRepository.findFirstByBookId(1));
    }
}