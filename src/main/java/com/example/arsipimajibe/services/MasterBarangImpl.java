package com.example.arsipimajibe.services;

import com.example.arsipimajibe.models.MasterData;
import com.example.arsipimajibe.repository.MasterBarangService;
import com.example.arsipimajibe.repository.MasterProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MasterBarangImpl implements MasterBarangService {
 private final MasterProductRepository masterProductRepository;

    public MasterBarangImpl(MasterProductRepository masterProductRepository) {
        this.masterProductRepository = masterProductRepository;
    }

    @Override
    public MasterData Post(MasterData params) {
        params.setId(UUID.randomUUID().toString());
        return getMasterData(params, params.getNamaBarang(), params.getStock(), params.getHarga(), params.getDeskripsi(), params.getPathPhoto(), params.getKategori(), params.getLokasi());
    }

    private MasterData getMasterData(MasterData params, String namaBarang, Integer stock, Integer harga, String deskripsi, String pathPhoto, String kategori, String lokasi) {
        params.setNamaBarang(namaBarang);
        params.setStock(stock);
        params.setHarga(harga);
        params.setDeskripsi(deskripsi);
        params.setPathPhoto(pathPhoto);
        params.setKategori(kategori);
        params.setLokasi(lokasi);
        return masterProductRepository.save(params);
    }

    @Override
    public MasterData Get(String id) {
        return masterProductRepository.findById(id).orElse(null);
    }

    @Override
    public MasterData Update(MasterData params, String id) {
        MasterData data = masterProductRepository.findById(id).orElse(null);
        assert data != null;
        return getMasterData(data, params.getNamaBarang(), params.getStock(), params.getHarga(), params.getDeskripsi(), params.getPathPhoto(), params.getKategori(), params.getLokasi());
    }

    @Override
    public String Delete(String id) {
       masterProductRepository.deleteById(id);
       return "Barang ("+id+")"+ " Telah di Hapus";
    }

    public List<MasterData> listAll() {
        return masterProductRepository.findAll();
    }
}
