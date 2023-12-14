package org.project.java.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Profit extends Transaction {

    public Profit(String description, BigDecimal amount, LocalDateTime timestamp) {
        super(description, amount, true);
        setTimestamp(timestamp);
    }


	@Override
    public BigDecimal changeCount(BigDecimal count) {
        return count.add(getAmount());
    }
}
