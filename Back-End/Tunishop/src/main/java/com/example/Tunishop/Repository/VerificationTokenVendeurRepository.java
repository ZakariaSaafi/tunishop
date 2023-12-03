package com.example.Tunishop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Tunishop.Entity.VerificationTokenVendeur;

@Repository
public interface VerificationTokenVendeurRepository extends JpaRepository<VerificationTokenVendeur, Long> {

	VerificationTokenVendeur findByToken(String token);

}
