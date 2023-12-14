package org.project.java.transaction;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Transaction {
    private String description;
    private BigDecimal amount;
    private boolean isProfit;
    private LocalDateTime timestamp;

    public Transaction(String description, BigDecimal amount, boolean isProfit) {
        setDescription(description);
        setAmount(amount);
        setProfit(isProfit);
        setTimestamp(LocalDateTime.now()); 
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    protected void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public abstract BigDecimal changeCount(BigDecimal finalCount);

    protected String formatMoney() {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(getAmount());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTimestamp = getTimestamp().format(formatter);
        return " descrizione: " + getDescription() + "\n importo: " + formatMoney() + " € \n data: " + formattedTimestamp + "\n --- \n";
    }
}
