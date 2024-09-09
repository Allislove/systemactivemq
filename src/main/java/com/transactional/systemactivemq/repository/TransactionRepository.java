package com.transactional.systemactivemq.repository;

import com.mongodb.lang.NonNullApi;
import com.transactional.systemactivemq.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@io.micrometer.common.lang.NonNullApi
public interface TransactionRepository extends MongoRepository<Transaction, String>, TransactionRepositoryCustom {
    List<Transaction> findAll();

}
