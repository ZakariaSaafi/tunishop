package com.example.Tunishop.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Vendeur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code_vendeur;
	private String nom;
	private String prenom;
	
	@Column(length = 60)
	private String mdp;//mot de passe
	private String role;//( ADMIN , LIVREUR , ACHETEUR , VENDEUR ) 
	private String email;
	
	private boolean enabled= false;//pour l'activation du compte de l'utilisateur ( true = activé )
	private boolean logged=false;//pour la vérification du processus de login
	private boolean isBanned=false;
	
	@OneToMany(mappedBy="vendeur")
	private List<Produit>produits;
	
	//GETTERS && SETTERS
	
	

	

	public Long getCode_vendeur() {
		return code_vendeur;
	}

	public void setCode_vendeur(Long code_vendeur) {
		this.code_vendeur = code_vendeur;
	}

	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}


}
