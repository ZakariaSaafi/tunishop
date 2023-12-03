package com.example.Tunishop.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Livreur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long code_livreur;
	private String nom;
	private String prenom;
	@Column(length = 60)
	private String mdp;//mot de passe
	private String role;//( ADMIN , LIVREUR , ACHETEUR , VENDEUR ) 
	private String email;
	private String region;
	private boolean enabled= false;//pour l'activation du compte de l'utilisateur ( true = activé )
	private boolean logged=false;//pour la vérification du processus de login
	private boolean isBanned=false;
	
	
	//GETTERS && SETTERS
	
	

	
	public Long getCode_livreur() {
		return code_livreur;
	}

	public void setCode_livreur(Long code_livreur) {
		this.code_livreur = code_livreur;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	

}
