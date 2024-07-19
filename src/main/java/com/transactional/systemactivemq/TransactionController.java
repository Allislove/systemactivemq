package com.transactional.systemactivemq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    // Instanciamos el servicio
    @Autowired
    private TransactionService transactionService;

    // Creamos pues una metodo para que resuelva las solicitudes http de tipo get
    // Y buscamos desde el metodo escrito en el servicio
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        transactionService.sendTransactionMessage(transaction);
       // return transactionService.addTransaction(transaction);
        return transaction;
    }

    @GetMapping("/daily-totals")
    public ResponseEntity<List<DailyTransactionTotal>> getDailyTransactionTotals() {
        List<DailyTransactionTotal> totals = transactionService.getDailyTransactionTotals();
        return ResponseEntity.ok(totals);
    }

    @PostMapping("/calculate-daily-totals")
    public void calculateDailyTotals() {
        transactionService.calculateAndSaveDailyTotals();
    }
}

