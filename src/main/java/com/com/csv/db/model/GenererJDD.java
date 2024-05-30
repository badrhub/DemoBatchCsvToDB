package com.com.csv.db.model;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.LongStream;

public class GenererJDD {

	public static void main(String[] args) throws FileNotFoundException {
	
		
		Random random = new Random();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		File file = new File("transactions");
		
		try(PrintWriter pw  = new PrintWriter(file)){
			
			pw.println("numero_lot;id_client;nom_produit;quantite;date_transaction;montant");
			
			LongStream.rangeClosed(1, 10000)
			.forEach(n -> {
				ZeTransaction zeTransaction = new ZeTransaction();
				 String idClient = "CL" + random.nextInt(9000) + 1000;
				String nomProduit = "Produit" + random.nextInt(100);
				Integer quantite = random.nextInt(100) + 1;
				LocalDate dateTransaction = LocalDate.now().minusDays(random.nextInt(365));
				Double montant = random.nextDouble() * 990 + 10;
				 
				zeTransaction.setNumero_lot(n);
				zeTransaction.setId_client(idClient);
				zeTransaction.setNom_produit(nomProduit);
				zeTransaction.setMontant(montant);
				zeTransaction.setQuantite(quantite);
				zeTransaction.setDate_transaction(dateTransaction);
				
				pw.println(zeTransaction.toCsvString());
				
			});
		}

	}

}
