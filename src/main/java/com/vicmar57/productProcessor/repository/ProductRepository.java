package com.vicmar57.productProcessor.repository;

import com.vicmar57.productProcessor.entity.ProductDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductDo, Long> {

}
