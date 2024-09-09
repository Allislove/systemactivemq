package com.transactional.systemactivemq.repository;

import com.transactional.systemactivemq.model.DailyTransactionTotal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepositoryCustom {
    List<DailyTransactionTotal> findDailyTransactionTotals();
}
