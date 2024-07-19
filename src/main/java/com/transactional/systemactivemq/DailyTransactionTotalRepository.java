package com.transactional.systemactivemq;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyTransactionTotalRepository extends MongoRepository<DailyTransactionTotal, String> {
    Optional<DailyTransactionTotal> findByDate(LocalDate date);
}
