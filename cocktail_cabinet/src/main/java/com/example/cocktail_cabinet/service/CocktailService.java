package com.example.cocktail_cabinet.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import com.example.cocktail_cabinet.dto.CocktailDTO;
import com.example.cocktail_cabinet.model.CocktailEntity;
import com.example.cocktail_cabinet.repository.CocktailRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CocktailService {
	
	@Autowired
	private CocktailRepository cocktailRepository;
	
	//번역 api 사용
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${deepl.api.key}")
	private String apiKey;
	
	public List<CocktailDTO> getAllCocktails() {
		List<CocktailEntity> cocktails = cocktailRepository.findAll();
		return cocktails.stream().map(this::convertAndTranslateDTO).collect(Collectors.toList());
	}
	
	private CocktailDTO convertAndTranslateDTO(CocktailEntity cocktail) {
		String translatePreparation = translateText(cocktail.getPreparation(), "ko");
		
		return CocktailDTO.builder()
				.CocktailName(cocktail.getCocktailName())
				.Bartender(cocktail.getBartender())
				.BarCompany(cocktail.getBarCompany())
				.Location(cocktail.getLocation())
				.Ingredients(cocktail.getIngredients())
				.Garnish(cocktail.getGarnish())
				.Glassware(cocktail.getGlassware())
				.preparation(translatePreparation)
				.Notes(cocktail.getNotes())
				.id(cocktail.getId())
				.build();
	}
	
	private String translateText(String text, String targetLang) {
		String url = "https://api-free.deepl.com/v2/translate";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "DeepL-Auth-Key " + apiKey);
		
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
	    params.add("text", text);
		params.add("target_lang", targetLang);
		
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);
		
		//DeepL API 호출
		ResponseEntity<Map> response = restTemplate.postForEntity(url, requestEntity, Map.class);
		
	    if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
	        throw new RuntimeException("Forbidden: Check your API key and usage limits.");
	    }
		
		//번역된 텍스트 추출
		Map responseBody = response.getBody();
		List<Map> translations = (List<Map>) responseBody.get("translations");
		return (String) translations.get(0).get("text");
	}
}