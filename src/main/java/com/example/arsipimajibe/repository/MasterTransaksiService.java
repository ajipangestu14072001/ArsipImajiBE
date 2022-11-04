package com.example.arsipimajibe.repository;

import com.example.arsipimajibe.models.MasterTransaksi;
import org.springframework.stereotype.Component;

@Component
public interface MasterTransaksiService {
    MasterTransaksi Post(MasterTransaksi params);
    MasterTransaksi Get(String id);
}
