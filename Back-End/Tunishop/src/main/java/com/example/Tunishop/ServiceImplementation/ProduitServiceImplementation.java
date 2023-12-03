package com.example.Tunishop.ServiceImplementation;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.Tunishop.Entity.Acheteur;
import com.example.Tunishop.Entity.Categorie;

import com.example.Tunishop.Entity.Produit;
import com.example.Tunishop.Entity.Vendeur;
import com.example.Tunishop.Repository.CategorieRepository;
import com.example.Tunishop.Repository.ProduitRepository;
import com.example.Tunishop.Repository.VendeurRepository;
import com.example.Tunishop.Service.ProduitService;


@Service
public class ProduitServiceImplementation implements ProduitService {

	@Autowired
	private ProduitRepository produitRepository;

	@Autowired
	private CategorieRepository categorieRepository;
	
	@Autowired
	private VendeurRepository vendRep;
	
	
	
	
	
	
	@Override
	@Transactional
	public void creerProduit(Produit produit, Long id_vendeur,Long id_categorie) {
		produit.setVendeur(vendRep.getById(id_vendeur));
		Categorie cat=categorieRepository.getById(id_categorie);
		Set<Categorie> produitCategorie=new HashSet<>();
		produitCategorie.add(cat);
		produit.setCategories(produitCategorie);
		produitRepository.save(produit);
	}

	@Override
	@Transactional
	public void updateProduit(Produit produit) {
		produitRepository.save(produit);		
	}

	@Override
	public List<Produit> ListProduit() {
		return produitRepository.findAll();
	}

	@Override
	public void supprimerProduit(Long Id) {
		produitRepository.deleteById(Id);
	}

	@Override
	public Optional<Produit> findProduit(Long Id) {
		return produitRepository.findById(Id);
	}

	@Override
	public List<Produit> ListProduitByCategorie(Long id_categorie) {
		Categorie cat=categorieRepository.getById(id_categorie);
		return produitRepository.findByCategories(cat);
	}

	@Override
	@Transactional
	public List<Produit> ListProduitByVendeur(Optional<Vendeur> v) {
		return produitRepository.findByVendeur(v.get());
	}
	@Override
	@Transactional
	public List<Produit> ListProduitByAcheteur(Acheteur a) {
		return produitRepository.findByAcheteur(a);
	}

	@Override
	public void initialisationProduit() {
	}



	
	
	
}
