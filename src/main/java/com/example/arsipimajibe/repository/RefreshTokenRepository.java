package com.example.arsipimajibe.repository;

import com.example.arsipimajibe.models.RefreshToken;
import com.example.arsipimajibe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Override
    Optional<RefreshToken> findById(Long id);
    Optional<RefreshToken> findByToken(String token);
    @Modifying
    int deleteByUser(User user);
}
