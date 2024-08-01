package com.example.cocktail_cabinet.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cocktail_cabinet.dto.CocktailDTO;
import com.example.cocktail_cabinet.model.CocktailEntity;
import com.example.cocktail_cabinet.repository.CocktailRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CocktailService {
	
	@Autowired
	private CocktailRepository cocktailRepository;
	
	public List<CocktailDTO> getAllCocktails() {
		List<CocktailEntity> cocktails = cocktailRepository.findAll();
		return cocktails.stream().map(this::convertDTO).collect(Collectors.toList());
	}
	
	private CocktailDTO convertDTO(CocktailEntity cocktail) {
		return CocktailDTO.builder()
				.CocktailName(cocktail.getCocktailName())
				.Bartender(cocktail.getBartender())
				.BarCompany(cocktail.getBarCompany())
				.Location(cocktail.getLocation())
				.Ingredients(cocktail.getIngredients())
				.Garnish(cocktail.getGarnish())
				.Glassware(cocktail.getGlassware())
				.preparation(cocktail.getPreparation())
				.Notes(cocktail.getNotes())
				.id(cocktail.getId())
				.build();
	}
}