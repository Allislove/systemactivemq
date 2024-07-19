package com.transactional.systemactivemq;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyTransactionTotal {

    @Getter
    @Setter
    private LocalDate date;
    private BigDecimal totalAmount;
}
