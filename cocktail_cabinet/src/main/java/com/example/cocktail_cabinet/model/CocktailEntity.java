package com.example.cocktail_cabinet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="cocktails")
public class CocktailEntity {
	@Id
	private String CocktailName;
	
	private String Bartender;
	
	private String BarCompany;
	
	private String Location;
	
	private String Ingredients;
	
	private String Garnish;
	
	private String Glassware;
	
	private String preparation;
	 
	private String Notes;
	
}