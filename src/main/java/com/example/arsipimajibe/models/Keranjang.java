package com.example.arsipimajibe.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "keranjang")
public class Keranjang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String idBarang;
    private String idUsers;
    private String namaBarang;
    private Integer harga;
    private String deskripsi;
    private String pathPhoto;
    private String kategori;
    private String lokasi;
}
