package com.example.arsipimajibe.repository;


import com.example.arsipimajibe.models.Keranjang;
import org.springframework.stereotype.Component;

@Component
public interface MasterKeranjangService {
    Keranjang Post(Keranjang params);
    String Delete(Integer id);
    Keranjang Get(Integer id);


}
