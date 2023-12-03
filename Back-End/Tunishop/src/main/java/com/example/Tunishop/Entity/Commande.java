package com.example.Tunishop.Entity;

import java.util.Date;
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
import javax.persistence.ManyToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "code_commande")
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code_commande;
	private String matricule;
	private String region;
	private String statut;
	private float devis;
	private Date date;
	private boolean isDelivered;
	
	@ManyToMany(cascade =CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="commande_livreur"
				,joinColumns=@JoinColumn (name="livreur_id"),
				inverseJoinColumns = @JoinColumn(name="commande_id")) 
	private List<Livreur> livreurs;
	
	@ManyToOne (fetch = FetchType.EAGER)
	@JoinColumn(name= "acheteur_code",
	nullable=true,
	foreignKey=@ForeignKey(name="FK_COMMANDE_ACHETEUR"))
	private Acheteur acheteur;
	
	@ManyToMany(cascade =CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="produits_commande"
	,joinColumns=@JoinColumn (name="commande_id"),
	inverseJoinColumns = @JoinColumn(name="produit_id"),
	foreignKey=@ForeignKey(name="FK_COMMANDE_PRODUIT")) 
	private List<Produit> produits;

	public Long getCode_commande() {
		return code_commande;
	}

	public void setCode_commande(Long code_commande) {
		this.code_commande = code_commande;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public float getDevis() {
		return devis;
	}

	public void setDevis(float devis) {
		this.devis = devis;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Acheteur getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Acheteur acheteur) {
		this.acheteur = acheteur;
	}

	public boolean isDelivered() {
		return isDelivered;
	}

	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}


	
	
	
}
