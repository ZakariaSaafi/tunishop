package com.example.Tunishop.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Tunishop.Entity.Panier;
import com.example.Tunishop.Entity.Produit;
import com.example.Tunishop.Repository.PanierRepository;
import com.example.Tunishop.Service.PanierService;
import com.example.Tunishop.Service.ProduitService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value="/Panier")
public class PanierController {
	
	@Autowired
	private	PanierService panierServ;
	
	@Autowired
	private	ProduitService produitServ;
	
	@Autowired
	private PanierRepository panierRep;
	

	
	@PostMapping("/AddToPanier/{id_acheteur}/{id_produit}")
	public Panier ajouterAuPanier(@Validated @RequestBody Panier panier,@PathVariable(value="id_acheteur") String id_acheteur
			,@PathVariable(value="id_produit") String id_produit)
	{
		if(produitServ.findProduit(Long.parseLong(id_produit)).get().getQuantite()>0) {
		panierServ.ajoutAuPanier(panier, Long.parseLong(id_acheteur), Long.parseLong(id_produit));
		}
		return panier;
	}
	
	
	@PutMapping("/UpdatePanier/{id_acheteur}/{id_produit}")
	public void UpdatePanier(@Validated @PathVariable(value="id_acheteur") String id_acheteur
			,@PathVariable(value="id_produit") String id_produit)
	{
		if(produitServ.findProduit(Long.parseLong(id_produit)).get().getQuantite()>0) {
		panierServ.updatePanier(Long.parseLong(id_acheteur), Long.parseLong(id_produit));
		}
	}
	
	@GetMapping("/getPanier/{id_acheteur}")
	public Panier getPanierByAcheteurId(@Validated @PathVariable(value="id_acheteur") String id_acheteur) {
		return panierServ.findPanier(Long.parseLong(id_acheteur));
	}
	@GetMapping("/getProduitPanier/{id_panier}")
	public List<Produit> getProduitByPanierId(@Validated @PathVariable(value="id_panier") String id_panier) {
		return panierServ.listProduitPanier(panierRep.findById(Long.parseLong(id_panier)).get());
	}

	
}
