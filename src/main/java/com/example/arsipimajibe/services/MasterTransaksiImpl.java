package com.example.arsipimajibe.services;

import com.example.arsipimajibe.models.MasterTransaksi;
import com.example.arsipimajibe.repository.MasterTransaksiRepository;
import com.example.arsipimajibe.repository.MasterTransaksiService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MasterTransaksiImpl implements MasterTransaksiService {
    final
    MasterTransaksiRepository masterTransaksiRepository;

    public MasterTransaksiImpl(MasterTransaksiRepository masterTransaksiRepository) {
        this.masterTransaksiRepository = masterTransaksiRepository;
    }

    @Override
    public MasterTransaksi Post(MasterTransaksi params) {
        params.setIdTransaksi(UUID.randomUUID().toString());
        params.setIdBarang(params.getIdBarang());
        params.setIdUser(params.getIdUser());
        params.setTypePembayaran(params.getTypePembayaran());
        params.setTotalHarga(params.getTotalHarga());
        params.setStatus(params.getStatus());
        return masterTransaksiRepository.save(params);
    }

    @Override
    public MasterTransaksi Get(String idUser) {
        return masterTransaksiRepository.findById(idUser).orElse(null);

    }

    public List<MasterTransaksi> listAll() {
        return masterTransaksiRepository.findAll();
    }
}
