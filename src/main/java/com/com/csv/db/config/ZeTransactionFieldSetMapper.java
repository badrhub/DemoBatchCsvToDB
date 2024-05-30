package com.com.csv.db.config;

import org.springframework.batch.item.file.transform.FieldSet;

import com.com.csv.db.model.ZeTransaction;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ZeTransactionFieldSetMapper implements FieldSetMapper<ZeTransaction> {
   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
   
  // numero_lot;id_client;nom_produit;quantite;date_transaction;montant
   
   @Override
   public ZeTransaction mapFieldSet(FieldSet fieldSet) {
       ZeTransaction zeTransaction = new ZeTransaction();
       zeTransaction.setNumero_lot(fieldSet.readLong(0));
       zeTransaction.setId_client(fieldSet.readString(1));
       zeTransaction.setNom_produit(fieldSet.readString(2));
       zeTransaction.setQuantite(fieldSet.readInt(3));
       zeTransaction.setDate_transaction(LocalDate.parse(fieldSet.readString(4), formatter));
       zeTransaction.setMontant(fieldSet.readDouble(5));
       return zeTransaction;
   }
}
