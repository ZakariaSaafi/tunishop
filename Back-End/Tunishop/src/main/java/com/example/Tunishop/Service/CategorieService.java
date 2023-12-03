package com.example.Tunishop.Service;

import java.util.List;
import java.util.Optional;

import com.example.Tunishop.Entity.Categorie;

public interface CategorieService {

	void creerCategorie(Categorie Categorie);
	void updateCategorie(Categorie Categorie);
	public List<Categorie>ListCategorie();
	void supprimerCategorie(Long Id);
	public Optional<Categorie> findCategorie(Long Id);
	
}
