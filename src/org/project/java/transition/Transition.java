package org.project.java.transition;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public abstract class Transition {
    private String description;
    private BigDecimal money;

    public Transition(String description, BigDecimal money) {
        setDescription(description);
        setMoney(money);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public abstract BigDecimal changeCount(BigDecimal finalCount);
    
    protected String formatMoney() {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(getMoney());
    }

    @Override
    public String toString() {
        return " descrizione= " + getDescription() + "\n importo= " + formatMoney() + " €  \n --- \n";
    }
}

