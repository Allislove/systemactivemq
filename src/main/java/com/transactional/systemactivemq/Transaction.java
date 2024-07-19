package com.transactional.systemactivemq;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Document(collection = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RestController
public class Transaction implements Serializable {

    @Getter
    @Setter
    @Id
    private String transactionId;
    private LocalDateTime timestamp;
    private String deviceNumber;
    private String userId;
    private String geoPosition;
    private BigDecimal amount;
    @Serial
    private static final long serialVersionUID = 1L;

}
