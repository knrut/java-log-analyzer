package com.knrut.loganalyzer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PurchaseEvent(
        LocalDateTime timestamp,
        String userId,
        BigDecimal amount,
        String item
) implements Event {}
