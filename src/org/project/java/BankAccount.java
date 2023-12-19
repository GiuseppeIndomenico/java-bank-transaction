package org.project.java;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import org.project.java.transaction.Cost;
import org.project.java.transaction.Profit;
import org.project.java.transaction.Transaction;
import org.project.java.transaction.TransactionManager;

public class BankAccount {
    private static final TransactionManager transactionManager = new TransactionManager();
    
    public static void main(String[] args) throws IOException {
    	BigDecimal finalCount;
    	List<Transaction> transactions = TransactionManager.loadTransactionsFromFile();
    	if (!transactions.isEmpty()) {
    	    finalCount = transactions.stream()
    	            .map(transaction -> transaction.isProfit() ? transaction.getAmount() : transaction.getAmount().negate())
    	            .reduce(BigDecimal.ZERO, BigDecimal::add);
    	} else {
    	    finalCount = BigDecimal.ZERO;
    	}

    	DecimalFormat df = new DecimalFormat("#0.00");
    	Scanner sc = new Scanner(System.in);

        System.out.println("Benvenuto nel tuo conto bancario!");

        while (true) {
            try {
                System.out.println("\n ------------------------");
                System.out.println("1. Inserisci guadagno");
                System.out.println("2. Inserisci spesa");
                System.out.println("3. Visualizza tutte le transazioni");
                System.out.println("4. Stampa saldo finale");
                System.out.println("5. Esci");

                int choice = sc.nextInt();
                sc.nextLine(); // Consuma il newline residuo

                switch (choice) {
                    case 1:
                        System.out.println("\n ------------------------ \n");
                        System.out.println("Inserisci la descrizione del guadagno: ");
                        String profitDescription = sc.nextLine();

                        System.out.println("Inserisci l'importo del guadagno: ");
                        try {
                            String profitMoneyStr = sc.nextLine();
                            BigDecimal profitMoney = new BigDecimal(profitMoneyStr.trim());
                            Profit profit = new Profit(profitDescription, profitMoney, LocalDateTime.now());
                            transactions.add(profit);
                            finalCount = profit.changeCount(finalCount);
                           
                        } catch (IllegalArgumentException e) {
                            System.out.println("Errore: Inserisci un importo valido.");
                        }
                        break;

                    case 2:
                        System.out.println("\n ------------------------ \n");
                        System.out.println("Inserisci la descrizione della spesa: ");
                        String costDescription = sc.nextLine();

                        System.out.println("Inserisci l'importo della spesa: ");
                        try {
                            String costMoneyStr = sc.nextLine();
                            BigDecimal costMoney = new BigDecimal(costMoneyStr.trim());
                            Cost cost = new Cost(costDescription, costMoney, LocalDateTime.now());
                                             transactions.add(cost);
                            finalCount = cost.changeCount(finalCount);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Errore: Inserisci un importo valido.");
                        }
                        break;

                    case 3:
                        System.out.println("\n ------------------------ \n");
                        System.out.println("Transazioni inserite: \n");

                        for (int i = 0; i < transactions.size(); i++) {
                            Transaction transaction = transactions.get(i);
                            System.out.println("#" + (i + 1) + "-" + transaction);
                        }
                        break;
                    case 4:
                        System.out.println("\n ------------------------ \n");
                        System.out.println("Saldo finale: " + df.format(finalCount) + " €");
                        break;

                    case 5:
                        System.out.println("Grazie per aver utilizzato il nostro servizio. Arrivederci!");
                        TransactionManager.saveTransactionsToFile(transactions);
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Scelta non valida. Riprova.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Errore: Inserisci un numero valido.");
                sc.nextLine(); // Consuma l'input non valido
            }
        }
    }
}
