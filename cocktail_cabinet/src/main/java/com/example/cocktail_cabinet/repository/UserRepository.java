package com.example.cocktail_cabinet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cocktail_cabinet.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findByUserId(String userId);
	Boolean existsByUserId(String userId);
	UserEntity findByUserIdAndPassword(String userId, String password);
}