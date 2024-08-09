package com.example.cocktail_cabinet.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.cocktail_cabinet.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import javax.crypto.SecretKey;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenProvider{

    @Value("${jwt.secret}")
    private String secretKeyString;

    private SecretKey SECRET_KEY;

    @PostConstruct
    protected void init() {
        byte[] decodedKey = Base64.getDecoder().decode(secretKeyString);
        this.SECRET_KEY = Keys.hmacShaKeyFor(decodedKey);
    }
    
	public String create(UserEntity userEntity) {
		Date expiryDate = Date.from(
				Instant.now()
					.plus(365, ChronoUnit.DAYS));
		
		String token = Jwts.builder()
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.setSubject(String.valueOf(userEntity.getId()))
				.setIssuer("Cocktail-cabinet")
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.compact();
        return token;
	}
	
	public String validateAndExtractUserId(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		
		return claims.getSubject();
	}
}
