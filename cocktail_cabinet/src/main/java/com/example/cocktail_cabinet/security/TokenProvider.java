package com.example.cocktail_cabinet.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.cocktail_cabinet.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenProvider{
	private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final String BASE64_SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getEncoded());
	
	public String create(UserEntity userEntity) {
		Date expiryDate = Date.from(
				Instant.now()
					.plus(365, ChronoUnit.DAYS));
		
		//JWT Token 생성
		return Jwts.builder()
                .signWith(SECRET_KEY)
                .setSubject(String.valueOf(userEntity.getId()))
                .setIssuer("cocktail_cabinet app")
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .compact();
	}
	
	public String validateAndGetUserId(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		
		return claims.getSubject();
	}
}