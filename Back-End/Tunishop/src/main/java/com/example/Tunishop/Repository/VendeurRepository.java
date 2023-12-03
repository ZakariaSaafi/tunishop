package com.example.Tunishop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.Tunishop.Entity.Vendeur;

@Repository
public interface VendeurRepository extends JpaRepository<Vendeur, Long> {

	Vendeur findByEmail(String email);

	boolean existsByEmail(String email);

}
