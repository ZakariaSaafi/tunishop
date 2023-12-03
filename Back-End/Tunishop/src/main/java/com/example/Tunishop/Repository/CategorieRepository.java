package com.example.Tunishop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Tunishop.Entity.Categorie;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long>{

}
