package com.transactional.systemactivemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionRepositoryCustom transactionRepositoryCustom;

    @Autowired
    private DailyTransactionTotalRepository dailyTransactionTotalRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction sendTransactionMessage(Transaction transaction) {
        // Primero guardamos la transacción en MongoDB
        Transaction savedTransaction = transactionRepository.save(transaction);
        // Enviamos la transacción al broker
        jmsTemplate.convertAndSend("transactionQueue", savedTransaction);
        return savedTransaction;
    }

    public List<DailyTransactionTotal> getDailyTransactionTotals() {
        return transactionRepositoryCustom.findDailyTransactionTotals();
    }

    public void calculateAndSaveDailyTotals() {
        List<DailyTransactionTotal> dailyTotals = transactionRepositoryCustom.findDailyTransactionTotals();
        for (DailyTransactionTotal total : dailyTotals) {
            dailyTransactionTotalRepository.findByDate(total.getDate())
                    .ifPresentOrElse(existingTotal -> {
                        existingTotal.setTotalAmount(existingTotal.getTotalAmount().add(total.getTotalAmount()));
                        dailyTransactionTotalRepository.save(existingTotal);
                    }, () -> dailyTransactionTotalRepository.save(total));
        }
    }
}
