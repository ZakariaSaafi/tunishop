package com.example.Tunishop.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Tunishop.Entity.Acheteur;
import com.example.Tunishop.Entity.Commande;
import com.example.Tunishop.Entity.Livreur;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

	List<Commande> findByAcheteur(Acheteur ach);

	List<Commande> findAllByLivreurs(Livreur livreur);

	

}
