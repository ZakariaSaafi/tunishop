package com.example.Tunishop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Tunishop.Entity.Livreur;

@Repository
public interface LivreurRepository extends JpaRepository<Livreur, Long> {

	Livreur findByEmail(String email);

	boolean existsByEmail(String email);

}