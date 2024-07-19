package com.transactional.systemactivemq;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class TransactionRepositoryCustomImpl implements TransactionRepositoryCustom {

    @Autowired
    private MongoClient mongoClient;

    @Override
    public List<DailyTransactionTotal> findDailyTransactionTotals() {

        MongoDatabase database = mongoClient.getDatabase("transportemasivo");
        MongoCollection<Document> collection = database.getCollection("transactions");

        List<Document> pipeline = new ArrayList<>();

        // Agrupamos por fecha y sumamos los valores de las transacciones
        pipeline.add(new Document("$group", new Document("_id", new Document("date", new Document("$dateToString", new Document("format", "%Y-%m-%d").append("date", "$timestamp"))))
                .append("totalAmount", new Document("$sum", new Document("$toDecimal", "$amount")))));

        // Proyectamos los resultados
        pipeline.add(new Document("$project", new Document("_id", 0)
                .append("totalAmount", 1)
                .append("date", "$_id.date")));
        pipeline.add(new Document("$sort", new Document("date", 1)));

        List<DailyTransactionTotal> results = new ArrayList<>();

        // Ejecutamos la agregación
        for (Document doc : collection.aggregate(pipeline)) {
            LocalDate date = LocalDate.parse(doc.getString("date"));
            // Convertimos el totalAmount a BigDecimal
            BigDecimal totalAmount = new BigDecimal(doc.get("totalAmount").toString());
            results.add(new DailyTransactionTotal(date, totalAmount));
        }

        return results;
    }
}
