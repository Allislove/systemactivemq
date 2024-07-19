package com.transactional.systemactivemq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionRepositoryCustom transactionRepositoryCustom;

    @Autowired private JmsTemplate jmsTemplate;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    // Metodo para crear las transacciones
    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<DailyTransactionTotal> getDailyTransactionTotals() { return transactionRepository.findDailyTransactionTotals(); }

    @Autowired
    private DailyTransactionTotalRepository dailyTransactionTotalRepository;

    public void calculateAndSaveDailyTotals() {
        List<DailyTransactionTotal> dailyTotals = transactionRepositoryCustom.findDailyTransactionTotals();
        for (DailyTransactionTotal total : dailyTotals) {
            dailyTransactionTotalRepository.findByDate(total.getDate())
                    .ifPresentOrElse(existingTotal -> {
                        existingTotal.setTotalAmount(existingTotal.getTotalAmount().add(total.getTotalAmount()));
                        dailyTransactionTotalRepository.save(existingTotal);
                    }, () -> {
                        dailyTransactionTotalRepository.save(total);
                    });
        }
    }

    public void sendTransactionMessage(Transaction transaction) {
        jmsTemplate.convertAndSend("transactionQueue", transaction);
    }
}
