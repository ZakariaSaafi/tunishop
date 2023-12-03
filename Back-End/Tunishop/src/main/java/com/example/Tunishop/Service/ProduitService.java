package com.example.Tunishop.Service;

import java.util.List;
import java.util.Optional;


import com.example.Tunishop.Entity.Acheteur;
import com.example.Tunishop.Entity.Produit;
import com.example.Tunishop.Entity.Vendeur;

public interface ProduitService {

	void creerProduit(Produit produit,Long id_vend,Long id_categorie);
	void updateProduit(Produit produit);
	List<Produit>ListProduit();
	void supprimerProduit(Long Id);
	public Optional<Produit> findProduit(Long Id);
	List<Produit> ListProduitByCategorie(Long id_categorie);
	List<Produit> ListProduitByVendeur(Optional<Vendeur> v);
	List<Produit> ListProduitByAcheteur(Acheteur a);
	public void initialisationProduit();
	
}
