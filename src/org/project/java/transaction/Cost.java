package org.project.java.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cost extends Transaction {

    public Cost(String description, BigDecimal amount, LocalDateTime timestamp) {
        super(description, amount, false);
        setTimestamp(timestamp);
    }

    @Override
    public BigDecimal changeCount(BigDecimal count) {
        return count.subtract(getAmount());
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = getTimestamp().format(formatter);
        return " descrizione: " + getDescription() + "\n importo: -" + formatMoney() + " € \n data: " + formattedTimestamp + "\n --- \n";
    }
}
