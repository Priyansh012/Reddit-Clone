package com.example.spring.reddit.clone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.reddit.clone.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);

}
