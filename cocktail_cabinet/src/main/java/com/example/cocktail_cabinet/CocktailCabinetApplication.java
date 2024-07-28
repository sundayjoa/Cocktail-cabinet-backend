package com.example.cocktail_cabinet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CocktailCabinetApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocktailCabinetApplication.class, args);
	}

}
