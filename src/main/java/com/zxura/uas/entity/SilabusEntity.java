package com.zxura.uas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "silabus")

public class SilabusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @ManyToOne
    @JoinColumn(name = "id_mapel", nullable = false)
    private MataPelajaranEntity mataPelajaranEntity;

    @Column(name = "judul", length = 50, nullable = true)
    String judul;

    @Column(name = "deskripsi", length = 50, nullable = true)
    String deskripsi;

    @Column(name = "tujuan", length = 50, nullable = true)
    String tujuan;

    @Column(name = "materi", length = 50, nullable = true)
    String materi;

    @Column(name = "metode", length = 50, nullable = true)
    String metode;

    @Column(name = "evaluasi", length = 50, nullable = true)
    String evaluasi;
}