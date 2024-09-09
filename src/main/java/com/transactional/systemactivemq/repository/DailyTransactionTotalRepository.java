package com.transactional.systemactivemq.repository;

import com.transactional.systemactivemq.model.DailyTransactionTotal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DailyTransactionTotalRepository extends MongoRepository<DailyTransactionTotal, String> {
    Optional<DailyTransactionTotal> findByDate(LocalDate date);
}
