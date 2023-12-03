package com.example.Tunishop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Tunishop.Entity.VerificationTokenAcheteur;

@Repository
public interface VerificationTokenAcheteurRepository extends JpaRepository<VerificationTokenAcheteur, Long> {

	VerificationTokenAcheteur findByToken(String token);

}
