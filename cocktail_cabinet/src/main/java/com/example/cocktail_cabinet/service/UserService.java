package com.example.cocktail_cabinet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cocktail_cabinet.model.UserEntity;
import com.example.cocktail_cabinet.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public UserEntity create(final UserEntity userEntity) {
		if(userEntity == null || userEntity.getUserId() == null) {
			throw new RuntimeException("Invalid arguments");
		}
		final String userId = userEntity.getUserId();
		if(userRepository.existsByUserId(userId)) {
			log.warn("Username already exists {}", userId);
			throw new RuntimeException("UserId already exists");
		}
		
		return userRepository.save(userEntity);
	}
	
	//비밀번호 암호화
	public UserEntity getByCredentials(final String userId, final String password, final PasswordEncoder encoder) {
		final UserEntity originalUser = userRepository.findByUserId(userId);
		
		if(originalUser != null &&
				encoder.matches(password, originalUser.getPassword())) {
			return originalUser;
		}
		return null;
	}
}