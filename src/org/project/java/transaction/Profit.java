package org.project.java.transaction;

import java.math.BigDecimal;

public class Profit extends Transaction {

    public Profit(String description, BigDecimal amount) {
        super(description, amount, true);
    }

    @Override
    public BigDecimal changeCount(BigDecimal count) {
        return count.add(getAmount());
    }
}
