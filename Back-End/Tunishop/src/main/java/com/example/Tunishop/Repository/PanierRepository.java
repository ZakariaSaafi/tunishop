package com.example.Tunishop.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Tunishop.Entity.Acheteur;
import com.example.Tunishop.Entity.Panier;
import com.example.Tunishop.Entity.Produit;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long>{

	Panier findByAcheteur(Acheteur byId);

}
