package com.example.Tunishop.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Categorie {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long code;
	private String nom;
	private String matricule;//pour l'identification de chaque categorie 

	

	public Categorie() {}
	
	public Categorie(String nom,String matricule) {
		super();
		this.nom = nom;
		this.matricule=matricule;
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

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	

	
	
}
