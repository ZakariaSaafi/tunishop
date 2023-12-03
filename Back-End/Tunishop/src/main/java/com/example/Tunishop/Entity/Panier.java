package com.example.Tunishop.Entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;


@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "code_panier")
public class Panier{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code_panier;
	private float prixTotal;


	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name= "acheteur_code",
	nullable=true,
	foreignKey=@ForeignKey(name="FK_PANIER_ACHETEUR"))
	private Acheteur acheteur;

	@ManyToMany(cascade =CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="produits_panier"
	,joinColumns=@JoinColumn (name="panier_id"),
	inverseJoinColumns = @JoinColumn(name="produit_id"),
	foreignKey=@ForeignKey(name="FK_PANIER_PRODUIT")) 
	private List<Produit> produits;
	
	public Panier() {}
	
	public Panier(float prixTotal) {
		super();
		this.prixTotal = prixTotal;
//		this.produitsPanier = produits;
	}



	public Long getCode_panier() {
		return code_panier;
	}

	public void setCode_panier(Long code_panier) {
		this.code_panier = code_panier;
	}

	public float getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(float prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Acheteur getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Acheteur acheteur) {
		this.acheteur = acheteur;
	}


	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}
	
	
	
	
}
