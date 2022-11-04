package com.example.arsipimajibe.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "master_transaksi")
public class MasterTransaksi {
    @Id
    private String idTransaksi;
    private String idBarang;
    private Long idUser;
    private String typePembayaran;
    private int totalHarga;
    private String status;
}
