package com.example.Tunishop.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Tunishop.Entity.Produit;
import com.example.Tunishop.Repository.AcheteurRepository;
import com.example.Tunishop.Service.ProduitService;
import com.example.Tunishop.Service.UserService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value="/Vendeur/Produit")
public class SecuredProduitController {

	@Autowired
	private ProduitService produitService;
	
	@Autowired
	private UserService userServ;
	
	@Autowired 
	private AcheteurRepository achRep;
	
	@PostMapping("/AddProduit/{id_vendeur}/{id_categorie}")
	public Produit creeProduit(@Validated @RequestBody Produit produit,@PathVariable(value="id_vendeur") String id_vend
			,@PathVariable(value="id_categorie") String id_categorie)
	{
		produitService.creerProduit(produit,Long.parseLong(id_vend),Long.parseLong(id_categorie));
		return produit;
	}
	
	@PutMapping("/UpdateProduit/{id}")
	public String UpdateProduit(@PathVariable ( value = "id") Long id ,@RequestBody Produit produit)
	{
		if(produitService.findProduit(id).isPresent()) {
			Produit produit_Existant= produitService.findProduit(id).get();
			produit_Existant.setNom(produit.getNom());
			produit_Existant.setDescription(produit.getDescription());
			produit_Existant.setQuantite(produit.getQuantite());
			produit_Existant.setPrix(produit.getPrix());
			produit_Existant.setVendu(produit.isVendu());
			produitService.updateProduit(produit_Existant);
			return "Mise à jour faite avec succés";
		}
		else
		{
			return "Pas de mise à jour";
		}
	}
	
	@GetMapping("/getAllVend/{id}" )
	public List<Produit> getAllVendeurProduits(@PathVariable(value="id") String id)
	{ 
		return produitService.ListProduitByVendeur(userServ.findVendeurById(Long.parseLong(id)));
		
	}
	@GetMapping("/getAllAch/{id}" )
	public List<Produit> getAllAcheteurProduits(@PathVariable(value="id") String id_acheteur)
	{ 
		return produitService.ListProduitByAcheteur(achRep.findById(Long.parseLong(id_acheteur)).get());
		
	}
	
	@DeleteMapping(value="/deleteProduit/{id}")
	public String deleteProduit(@PathVariable String id)
	{
		produitService.supprimerProduit(Long.parseLong(id));
		return "ok!";
	}
	
	@GetMapping(value="/findproduit/{id}")
	public Optional<Produit> findProduit(@PathVariable(value="id") String id){
		return produitService.findProduit(Long.parseLong(id));
	}

}
	
	

