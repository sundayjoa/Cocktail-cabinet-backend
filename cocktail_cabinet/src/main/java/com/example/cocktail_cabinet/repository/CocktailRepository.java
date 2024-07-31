package com.example.cocktail_cabinet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cocktail_cabinet.model.CocktailEntity;

@Repository
public interface CocktailRepository extends JpaRepository<CocktailEntity, String>{
	
}