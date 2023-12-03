package com.example.Tunishop.ServiceImplementation;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tunishop.Entity.Acheteur;
import com.example.Tunishop.Entity.Panier;
import com.example.Tunishop.Entity.Produit;
import com.example.Tunishop.Repository.AcheteurRepository;
import com.example.Tunishop.Repository.PanierRepository;
import com.example.Tunishop.Repository.ProduitRepository;
import com.example.Tunishop.Service.PanierService;
import com.example.Tunishop.Service.ProduitService;

@Service
public class PanierServiceImplementation implements PanierService{

	@Autowired
	private AcheteurRepository achRep;
	
	@Autowired
	private ProduitRepository produitRep;
	
	@Autowired
	private PanierRepository panierRepository;
	
	@Autowired
	private ProduitService produitServ;
	
	
	@Override
	@Transactional
	public void ajoutAuPanier(Panier panier ,Long id_acheteur, Long id_produit) {
		float prix=0.0f;
		panier.setAcheteur(achRep.getById(id_acheteur));
		Produit prod=produitRep.getById(id_produit);
		
		if(prod.getQuantite()>0) 
		{
			prod.setQuantite(prod.getQuantite()-1);
			Acheteur ach=achRep.findById(id_acheteur).get();
			List<Acheteur> list_ach=prod.getAcheteur();
			if(!list_ach.contains(ach))
			{
				list_ach.add(ach);
				prod.setAcheteur(list_ach);
			}
			
			produitServ.updateProduit(prod);
			prix+=prod.getPrix();		
			panier.setPrixTotal(prix);	
			List<Produit> list_prod=new ArrayList<Produit>();
			list_prod.add(prod);
			panier.setProduits(list_prod);
			panierRepository.save(panier);
		}//else
		
		if(prod.getQuantite()==0) 
		{
			prod.setVendu(true);
		}
	}

	@Override
	@Transactional
	public Panier updatePanier(Long id_acheteur, Long id_produit) { 
		//comment trouver le panier ?
		//panier passé en parametre est défini par l'id de l'acheteur
		float prix=0.0f;
		Panier panier=panierRepository.findByAcheteur(achRep.getById(id_acheteur));
		Produit prod=produitRep.getById(id_produit);
		if(prod.getQuantite()>0) 
		{
			prod.setQuantite(prod.getQuantite()-1);
			Acheteur ach=achRep.findById(id_acheteur).get();
			List<Acheteur> list_ach=prod.getAcheteur();
			if(!list_ach.contains(ach))
			{
				list_ach.add(ach);
				prod.setAcheteur(list_ach);
			}
			
			produitServ.updateProduit(prod);
			prix=panier.getPrixTotal();
			prix+=prod.getPrix();
			panier.setPrixTotal(prix);
			List<Produit> list_prod=panier.getProduits();
			list_prod.add(prod);
			panier.setProduits(list_prod);
			panierRepository.save(panier);
		}//else
		
		if(prod.getQuantite()==0) 
		{
			prod.setVendu(true);
		}
		
		return panier;
	}

	@Override
	public List<Panier> ListPanier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerPanier(Long Id) {
		panierRepository.deleteById(Id);
		
	}

	@Override
	@Transactional
	public Panier findPanier(Long id_acheteur) {
		return panierRepository.findByAcheteur(achRep.findById(id_acheteur).get()); 
	}
	
	@Override
	@Transactional
	public Panier findPanierPourCommande(Long id) {
		return panierRepository.findById(id).get(); 
	}

	

	@Override
	@Transactional
	public List<Produit> listProduitPanier(Panier p) {
		return p.getProduits();
	}

	
	
	
	

}
