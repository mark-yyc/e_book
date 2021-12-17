package com.mark.repository;

import com.mark.entity.View;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ViewRepository extends MongoRepository<View,Integer> {
}
