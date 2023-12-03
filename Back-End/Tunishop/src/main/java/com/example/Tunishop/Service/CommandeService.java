package com.example.Tunishop.Service;

import java.util.List;

import com.example.Tunishop.Entity.Commande;

public interface CommandeService {
	
	String creerCommande(Commande Commande,Long id_panier);
	Commande updateCommande(Long id_acheteur, Long id_produit);
	Commande findCommande(Long id_commande);
	void supprimerCommande(Long Id);
	public List<Commande> findCommandeByAcheteur(Long Id);
	void changerStatut(Long id_commande);
	List<Commande>ListCommande(); //pour admin
	List<Commande> ListCommandeLivreur(Long id_livreur);
	List<Commande> ListCommandeNotDelivered();
	
}
