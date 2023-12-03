package com.example.Tunishop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Tunishop.Entity.Acheteur;
import com.example.Tunishop.Entity.Categorie;
import com.example.Tunishop.Entity.Produit;
import com.example.Tunishop.Entity.Vendeur;
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>{

	List<Produit> findByCategories(Categorie cat);
	List<Produit> findByVendeur(Vendeur v);
	List<Produit> findByAcheteur(Acheteur acheteur);

}
