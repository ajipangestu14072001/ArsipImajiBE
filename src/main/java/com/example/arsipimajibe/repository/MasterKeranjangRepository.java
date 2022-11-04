package com.example.arsipimajibe.repository;

import com.example.arsipimajibe.models.Keranjang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterKeranjangRepository extends JpaRepository<Keranjang, Integer> {
    List<Keranjang> findByIdUsersContaining(String idUsers);

}
