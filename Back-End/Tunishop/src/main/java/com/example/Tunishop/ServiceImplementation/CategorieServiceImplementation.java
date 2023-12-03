package com.example.Tunishop.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tunishop.Entity.Categorie;
import com.example.Tunishop.Repository.CategorieRepository;
import com.example.Tunishop.Service.CategorieService;

@Service
public class CategorieServiceImplementation implements CategorieService{

	@Autowired
	private CategorieRepository categorieRepository;
	
	@Override
	public void creerCategorie(Categorie Categorie) {
		categorieRepository.save(Categorie);
	}

	@Override
	public void updateCategorie(Categorie Categorie) {
		categorieRepository.save(Categorie);
		
	}

	@Override
	public List<Categorie> ListCategorie() {
		return categorieRepository.findAll();
	}

	@Override
	public void supprimerCategorie(Long Id) {
		categorieRepository.deleteById(Id);
		
	}

	@Override
	public Optional<Categorie> findCategorie(Long Id) {
		return categorieRepository.findById(Id);
	}

}
