package com.example.cocktail_cabinet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cocktail_cabinet.dto.CocktailDTO;
import com.example.cocktail_cabinet.service.CocktailService;

@RestController
@RequestMapping("/cocktails")
public class CocktailController {
	@Autowired
	private CocktailService cocktailService;
	
	@GetMapping("/all")
	public List<CocktailDTO> getAllCocktails(){
		return cocktailService.getAllCocktails();
	}
}