package com.example.Tunishop.Entity;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;


@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "code")
public class Produit {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long code;
	private String nom;
	private String description;
	private float prix;
	private int quantite;
	private Date dateAjout;
	private boolean vendu;
	private boolean surCommande;
	@Lob
	private String image;
	
	
	
	//relation many to one entre produit et catégorie
	@ManyToMany(cascade =CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="produits_categories"
	,joinColumns=@JoinColumn (name="produit_id"),
	inverseJoinColumns = @JoinColumn(name="categorie_id"),
	foreignKey=@ForeignKey(name="FK_CATEGORIE_PRODUIT")) 
	private Set<Categorie> categories=new HashSet<>();
	
	//relation many to one entre produit et acheteur	
	@ManyToMany(cascade =CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="produits_acheteur"
	,joinColumns=@JoinColumn (name="produit_id"),
	inverseJoinColumns = @JoinColumn(name="acheteur_id"),
	foreignKey=@ForeignKey(name="FK_ACHETEUR_PRODUIT")) 
	private List<Acheteur> acheteur;
	
	//relation many to one entre produit et vendeur
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name= "vendeur_code",
	nullable=false,
	foreignKey=@ForeignKey(name="FK_VENDEUR_PRODUIT"))
	private Vendeur vendeur;
	
	public Produit() {}
	public Produit(Long id,String nom, String description, float prix, int quantite, Date dateAjout, boolean vendu) {
		super();
		this.code=id;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.quantite = quantite;
		this.dateAjout = dateAjout;
		this.vendu = vendu;
	}
	

	
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public Date getDateAjout() {
		return dateAjout;
	}
	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}
	public boolean isVendu() {
		return vendu;
	}
	public void setVendu(boolean vendu) {
		this.vendu = vendu;
	}

	public Set<Categorie> getCategories() {
		return categories;
	}
	public void setCategories(Set<Categorie> categories) {
		this.categories = categories;
	}
	
	public Vendeur getVendeur() {
		return vendeur;
	}
	public void setVendeur(Vendeur vendeur) {
		this.vendeur = vendeur;
	}
	public boolean isSurCommande() {
		return surCommande;
	}
	public void setSurCommande(boolean surCommande) {
		this.surCommande = surCommande;
	}
	public List<Acheteur> getAcheteur() {
		return acheteur;
	}
	public void setAcheteur(List<Acheteur> acheteur) {
		this.acheteur = acheteur;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	
	
	
}
