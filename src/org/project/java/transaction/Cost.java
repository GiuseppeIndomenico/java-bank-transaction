package org.project.java.transaction;

import java.math.BigDecimal;

public class Cost extends Transaction {

    public Cost(String description, BigDecimal amount) {
        super(description, amount, false);
    }

    @Override
    public BigDecimal changeCount(BigDecimal count) {
        return count.subtract(getAmount());
    }


    @Override
    public String toString() {
        return " descrizione= " + getDescription() + "\n importo= -" + formatMoney() + " €  \n";
    }
}
