package org.project.java.transition;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public abstract class Transition {
    private String description;
    private BigDecimal money;

    public Transition(String description, BigDecimal money) throws IllegalArgumentException {
        setDescription(description);
        setMoney(money);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IllegalArgumentException {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("La descrizione non può essere vuota o nullo.");
        }
        this.description = description;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) throws IllegalArgumentException {
        if (money == null || money.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("L'importo deve essere un valore positivo.");
        }
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

