package com.example.Tunishop.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()") 
public class HomeController {
	
	//Redirection à La Page d'un Acheteur
	@PreAuthorize("hasAuthority('ACHETEUR')")
	@GetMapping("/acheteur")
	public String redirectUserHomePage() {
		return "<h1> this is Acheteur home page </h1>";	
	}
	
	//Redirection à La Page d'un Vendeur

	@PreAuthorize("hasAuthority('VENDEUR')")
	@GetMapping("/vendeur")
	public String redirectUserHome1Page() {
		return "<h1> this is Vendeur home page </h1>";	
	}
	
	//Redirection à La Page d'un Livreur

	@GetMapping("/livreur")
	public String redirectUserHome2Page() {
		return "<h1> this is Livreur home page </h1>";	
	}
	
	//Redirection à La Page d'un admin

	@GetMapping("/admin")
	public String redirectAdminPage() {
		return "<h1> this is admin page </h1>";
		
	}

}
