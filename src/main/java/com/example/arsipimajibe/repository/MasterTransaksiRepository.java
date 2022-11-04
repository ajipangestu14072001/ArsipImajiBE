package com.example.arsipimajibe.repository;

import com.example.arsipimajibe.models.MasterTransaksi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterTransaksiRepository extends JpaRepository<MasterTransaksi, String> {
    List<MasterTransaksi> findByIdUser(Long idUser);

}
