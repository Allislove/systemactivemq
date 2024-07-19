package com.transactional.systemactivemq;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String>, TransactionRepositoryCustom { }
