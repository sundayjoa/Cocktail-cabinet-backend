package com.example.cocktail_cabinet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CocktailDTO {
	private String CocktailName;
	private String Bartender;
	private String BarCompany;
	private String Location;
	private String Ingredients;
	private String Garnish;
	private String Glassware;
	private String preparation;
	private String Notes;
	private Long id;
}