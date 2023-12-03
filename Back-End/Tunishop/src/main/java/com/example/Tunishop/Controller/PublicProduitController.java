package com.example.Tunishop.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Tunishop.Entity.Produit;
import com.example.Tunishop.Service.ProduitService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value="/Produit")
public class PublicProduitController {

	@Autowired
	private ProduitService produitService;
	
	@GetMapping("/getAll")
	public List<Produit> getAllProduits()
	{ 
		return produitService.ListProduit();
		
	}
	
	@GetMapping("/getAllProduitsFromCategorie/{id}")
	public List<Produit> getAllProduitsFromCategorie(@PathVariable(value="id") String id_categorie)
	{ 
		return produitService.ListProduitByCategorie(Long.parseLong(id_categorie));
		
	}
	
	@GetMapping(value="/findproduit/{id}")
	public Optional<Produit> findProduit(@PathVariable(value="id") String id){
		return produitService.findProduit(Long.parseLong(id));
	}
}
