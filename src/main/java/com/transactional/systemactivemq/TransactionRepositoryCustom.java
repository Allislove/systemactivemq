package com.transactional.systemactivemq;

import java.util.List;

public interface TransactionRepositoryCustom {
    List<DailyTransactionTotal> findDailyTransactionTotals();
}
