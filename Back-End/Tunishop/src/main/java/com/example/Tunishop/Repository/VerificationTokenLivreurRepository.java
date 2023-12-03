package com.example.Tunishop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Tunishop.Entity.VerificationTokenLivreur;


@Repository
public interface VerificationTokenLivreurRepository extends JpaRepository<VerificationTokenLivreur, Long> {

	VerificationTokenLivreur findByToken(String token);

}
