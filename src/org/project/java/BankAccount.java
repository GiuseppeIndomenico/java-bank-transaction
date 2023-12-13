package org.project.java;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.project.java.transition.Cost;
import org.project.java.transition.Profit;
import org.project.java.transition.Transition;

public class BankAccount {
	public static void main(String[] args) {
		BigDecimal finalCount = BigDecimal.ZERO;
		DecimalFormat df = new DecimalFormat("#0.00");
		List<Transition> transitions = new ArrayList<>();
		Scanner sc = new Scanner(System.in);

		System.out.println("Benvenuto nel tuo conto bancario!");

		while (true) {
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
				BigDecimal profitMoney = new BigDecimal(sc.next());
				sc.nextLine(); // Consuma il newline residuo
				Profit profit = new Profit(profitDescription, profitMoney);
				transitions.add(profit);
				finalCount = profit.changeCount(finalCount);
				break;

			case 2:
				System.out.println("\n ------------------------ \n");
				System.out.println("Inserisci la descrizione della spesa: ");
				String costDescription = sc.nextLine();
				System.out.println("Inserisci l'importo della spesa: ");
				BigDecimal costMoney = new BigDecimal(sc.next());
				sc.nextLine(); // Consuma il newline residuo
				Cost cost = new Cost(costDescription, costMoney);
				transitions.add(cost);
				finalCount = cost.changeCount(finalCount);
				break;

			case 3:
				System.out.println("\n ------------------------ \n");
				System.out.println("Transazioni inserite: \n");
				for (Transition transaction : transitions) {
					System.out.println(transaction);
				}
				break;
			case 4:
			    System.out.println("\n ------------------------ \n");
			    System.out.println("Saldo finale: " + df.format(finalCount) + " €");
			    break;

			case 5:
				System.out.println("Grazie per aver utilizzato il nostro servizio. Arrivederci!");
				System.exit(0);
				break;

			default:
				System.out.println("Scelta non valida. Riprova.");
			}
		}
	}
}