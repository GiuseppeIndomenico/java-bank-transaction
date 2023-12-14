package org.project.java.transaction;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public abstract class Transaction {
    private String description;
    private BigDecimal amount;
    private boolean isProfit; 

    public Transaction(String description, BigDecimal amount, boolean isProfit) {
        setDescription(description);
        setAmount(amount);
        setProfit(isProfit);
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
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) throws IllegalArgumentException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("L'importo deve essere un valore positivo.");
        }
        this.amount = amount;
    }
    public boolean isProfit() {
    	return isProfit;
    }
    
    
    public void setProfit(boolean isProfit) {
    	this.isProfit = isProfit;
    }
    public abstract BigDecimal changeCount(BigDecimal finalCount);
    protected String formatMoney() {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(getAmount());
    }

    @Override
    public String toString() {
        return " descrizione= " + getDescription() + "\n importo= " + formatMoney() + " €  \n --- \n";
    }


}

