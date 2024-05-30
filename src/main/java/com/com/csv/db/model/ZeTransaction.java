package com.com.csv.db.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
public class ZeTransaction {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long numero_lot;
	private String id_client;
	private String nom_produit;
	private Integer quantite;
	private Double montant;
	private LocalDate date_transaction;
	private Boolean traiterYN =false;
	private Boolean rejeterYN = false;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumero_lot() {
		return numero_lot;
	}
	public void setNumero_lot(Long numero_lot) {
		this.numero_lot = numero_lot;
	}
	public String getId_client() {
		return id_client;
	}
	public void setId_client(String id_client) {
		this.id_client = id_client;
	}
	public String getNom_produit() {
		return nom_produit;
	}
	public void setNom_produit(String nom_produit) {
		this.nom_produit = nom_produit;
	}
	public Integer getQuantite() {
		return quantite;
	}
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
	public Double getMontant() {
		return montant;
	}
	public void setMontant(Double montant) {
		this.montant = montant;
	}
	public LocalDate getDate_transaction() {
		return date_transaction;
	}
	public void setDate_transaction(LocalDate date_transaction) {
		this.date_transaction = date_transaction;
	}
	public Boolean getTraiterYN() {
		return traiterYN;
	}
	public void setTraiterYN(Boolean traiterYN) {
		this.traiterYN = traiterYN;
	}
	public Boolean getRejeterYN() {
		return rejeterYN;
	}
	public void setRejeterYN(Boolean rejeterYN) {
		this.rejeterYN = rejeterYN;
	}
	
	public String toCsvString() {
		//numero_lot;id_client;nom_produit;quantite;date_transaction;montant
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		return this.getNumero_lot() +";"+ this.getId_client() + ";"+ this.getNom_produit()+";"+this.getQuantite()+";"+ this.getDate_transaction().format(formatter) +";" +this.getMontant();
	}

}
