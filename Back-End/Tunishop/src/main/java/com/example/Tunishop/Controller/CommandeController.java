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

import com.example.Tunishop.Entity.Commande;
import com.example.Tunishop.Service.CommandeService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value="/Commande")
public class CommandeController {
	
	@Autowired
	private CommandeService commandeServ;

	@PostMapping("/creerCommande/{id_acheteur}")
	public String creerCommande(@Validated @RequestBody Commande commande
			,@PathVariable(value="id_acheteur") String id_acheteur)
	{
		return commandeServ.creerCommande(commande, Long.parseLong(id_acheteur));
	}
	
	
	@GetMapping("/getCommande/{id_acheteur}")
	public List<Commande> getCommandeByAcheteur(@Validated @PathVariable(value="id_acheteur") String id_acheteur) {
		return commandeServ.findCommandeByAcheteur(Long.parseLong(id_acheteur));
	}
	
	@GetMapping("/getAllCommande")
	public List<Commande> getAllCommande() {
		return commandeServ.ListCommande();
	}
	
	@GetMapping("/getCommandeById/{id_commande}")
	public Commande getCommandeById(@Validated @PathVariable(value="id_commande") String id_commande) {
		return commandeServ.findCommande(Long.parseLong(id_commande));
	}
	
	@PutMapping("/changerStatut/{id_commande}")
	public void changeStatut(@Validated @PathVariable(value="id_commande") String id_commande) {
		commandeServ.changerStatut(Long.parseLong(id_commande));
	}
	
	@GetMapping("/listCommandeLivreur/{id_livreur}")
	public List<Commande> getCommandeByLivreur(@Validated @PathVariable(value="id_livreur") String id_livreur){
		return commandeServ.ListCommandeLivreur(Long.parseLong(id_livreur));
	}
	
	@GetMapping("/listCommandeNotDelivered")
	public List<Commande> getCommandeByLivreur(){
		return commandeServ.ListCommandeNotDelivered();
	}
	
//	@GetMapping("/getCommandeLocationInMaps/{id_commande}")
//	public void getCommandeLocation(@Validated @PathVariable(value="id_commande") String id_commande) {
//		commandeServ.getLocationFromCommande(Long.parseLong(id_commande));
//	}
	

}
