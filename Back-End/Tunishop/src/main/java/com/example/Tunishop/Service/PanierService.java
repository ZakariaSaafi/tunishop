package com.example.Tunishop.Service;

import java.util.List;

import com.example.Tunishop.Entity.Panier;
import com.example.Tunishop.Entity.Produit;



public interface PanierService {
	void ajoutAuPanier(Panier Panier,Long id_ach,Long id_produit);
	Panier updatePanier(Long id_acheteur, Long id_produit);
	List<Panier>ListPanier();
	void supprimerPanier(Long Id);
	public Panier findPanier(Long Id);
	public Panier findPanierPourCommande(Long Id);
	List<Produit> listProduitPanier(Panier p);
}
