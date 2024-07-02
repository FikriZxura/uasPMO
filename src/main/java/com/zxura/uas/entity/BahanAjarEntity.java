package com.zxura.uas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bahan_ajar")

public class BahanAjarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @OneToOne
    @JoinColumn(name = "id_mapel")
    private MataPelajaranEntity mataPelajaranEntity;

    @Column(name = "judul", length = 255, nullable = true)
    String judul;

    @Column(name = "deskripsi", length = 255, nullable = true)
    String deskripsi;

    @Column(name = "tipe", length = 255, nullable = true)
    String tipe;

    @Column(name = "file", length = 255, nullable = true)
    String file;
}
