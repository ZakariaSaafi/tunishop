package com.example.Tunishop.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Tunishop.Entity.Categorie;
import com.example.Tunishop.Service.CategorieService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping(value="/Categorie")
public class PublicCategorieController {

	@Autowired
	CategorieService catServ;
	
	
	@GetMapping("/getAll" )
	public List<Categorie> getAllCategorie()
	{ 
		return catServ.ListCategorie();
		
	}
	
	
	@GetMapping(value="/findCategorie/{id}")
	public Optional<Categorie> findCategorie(@PathVariable(value="id") String id){
		return catServ.findCategorie(Long.parseLong(id));
	}

}
