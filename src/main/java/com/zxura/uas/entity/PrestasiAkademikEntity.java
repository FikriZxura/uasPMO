package com.zxura.uas.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "prestasiAkademik")
public class PrestasiAkademikEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "id_siswa", length = 50, nullable = true)
    int idSiswa;

    @Column(name = "tanggal", length = 50, nullable = true)
    Date tanggal;

    @Column(name = "jenis_prestasi", length = 50, nullable = true)
    String jenisPrestasi;

    @Column(name = "deskripsi_nilai", length = 50, nullable = true)
    String deskripsiNilai;

    @ManyToOne
    @JoinColumn(name = "id_mapel", nullable = false)
    private MataPelajaranEntity mataPelajaranEntity;
}
