package com.example.arsipimajibe.repository;

import com.example.arsipimajibe.models.ERole;
import com.example.arsipimajibe.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
