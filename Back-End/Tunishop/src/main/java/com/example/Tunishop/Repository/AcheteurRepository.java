package com.example.Tunishop.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Tunishop.Entity.Acheteur;

@Repository
public interface AcheteurRepository extends JpaRepository<Acheteur, Long>{

	//Optional<Acheteur> findByEmail(String mail);
	
	Acheteur findByEmail(String mail);

	boolean existsByEmail(String email);
}
