package org.project.java.transaction;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionManager {
	private static final String FILE_NAME = "transazioniSalvate.csv";
	private static final String FILE_PATH = System.getProperty("user.dir") + File.separator + FILE_NAME;




	public static void saveTransactionsToFile(List<Transaction> transactions) {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
	        System.out.println("Percorso del file: " + FILE_PATH);
	        System.out.println("Numero di transazioni: " + transactions.size());
	        for (Transaction transaction : transactions) {
	            writer.write(transaction.isProfit() + "," + transaction.getDescription() + "," + transaction.getAmount() + System.lineSeparator());
	        }
	    } catch (IOException e) {
	        e.printStackTrace();  
	        System.out.println("Errore nel salvataggio del file.");
	    }
	}




    public static List<Transaction> loadTransactionsFromFile() throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
         
            file.createNewFile();
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 3) {
                    String typeStr = parts[0].trim();
                    String description = parts[1].trim();
                    BigDecimal amount = new BigDecimal(parts[2].trim());

                    boolean isProfit = Boolean.parseBoolean(typeStr);
                    Transaction transaction;

                    if (isProfit) {
                        transaction = new Profit(description, amount);
                    } else {
                        transaction = new Cost(description, amount);
                    }

                    transactions.add(transaction);
                } else {
                    System.out.println("Errore nel formato della riga: " + line);
                }
            }
        } catch (FileNotFoundException e) {
        	  e.printStackTrace();
              System.out.println("Errore nel caricamento del file.");
        }

        return transactions;
    }
}
