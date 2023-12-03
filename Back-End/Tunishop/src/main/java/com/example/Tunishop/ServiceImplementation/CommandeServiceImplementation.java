package com.example.Tunishop.ServiceImplementation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tunishop.Entity.Commande;
import com.example.Tunishop.Entity.Panier;
import com.example.Tunishop.Entity.Produit;
import com.example.Tunishop.Repository.AcheteurRepository;
import com.example.Tunishop.Repository.CommandeRepository;
import com.example.Tunishop.Repository.LivreurRepository;
import com.example.Tunishop.Service.CommandeService;
import com.example.Tunishop.Service.PanierService;


@Service
public class CommandeServiceImplementation implements CommandeService{

	@Autowired
	private PanierService panierServ;
	@Autowired
	private CommandeRepository commandeRep;
	
	@Autowired
	AcheteurRepository achRep;
	
	@Autowired
	LivreurRepository livRep;
	
	@Override
	@Transactional
	public String creerCommande(Commande commande, Long id_acheteur) {
		Calendar cal=Calendar.getInstance();
		Random rnd=new Random();
		
		int n;
		
			Panier p=panierServ.findPanier(id_acheteur);
			commande.setRegion(p.getAcheteur().getRegion());
			commande.setDevis(p.getPrixTotal());
			commande.setStatut("Non payé");
			commande.setDate(cal.getTime());
			commande.setRegion(p.getAcheteur().getRegion());
			n=rnd.nextInt(9999)+1;
			commande.setMatricule(Integer.toString(n));
			commande.setAcheteur(p.getAcheteur());
			commande.setDelivered(false);
			List<Produit> list_prod_commande= new ArrayList<Produit>();
			for(Produit pro:p.getProduits()) {
				pro.setSurCommande(true);
				list_prod_commande.add(pro);
			}
			commande.setProduits(list_prod_commande);
			commandeRep.save(commande);
			panierServ.supprimerPanier(p.getCode_panier());
			return("commande enriegistré avec succés");
		
	}

	@Override
	public Commande updateCommande(Long id_acheteur, Long id_produit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Commande> ListCommande() {
		return commandeRep.findAll();
	}

	@Override
	public void supprimerCommande(Long Id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public List<Commande> findCommandeByAcheteur(Long Id) {
		return commandeRep.findByAcheteur(achRep.findById(Id).get());
	}

	public Date calculateExpirationTime(int expirationTime) {
		Calendar calendar=Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.HOUR, expirationTime);
		return new Date(calendar.getTime().getTime());
	}

	@Override
	public Commande findCommande(Long id_commande) {
		return commandeRep.findById(id_commande).get();
	}

	@Override
	public void changerStatut(Long id_commande) {
		Commande c=commandeRep.findById(id_commande).get();
		c.setStatut("Payé");
		commandeRep.save(c);
	}

	@Override
	public List<Commande> ListCommandeLivreur(Long id_livreur) {
		List<Commande> commandes_livreur=commandeRep.findAllByLivreurs(livRep.findById(id_livreur).get());
		return commandes_livreur;
	}

	@Override
	public List<Commande> ListCommandeNotDelivered() {
		List<Commande> commande_not_delivered=new ArrayList<>();
		for(Commande c:commandeRep.findAll()) {
			if(!c.isDelivered() && c.getStatut().equals("Payé")) {
				commande_not_delivered.add(c);
			}
		}
		return commande_not_delivered;
	}

	

	
	
	
}
