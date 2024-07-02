package com.zxura.uas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mata_pelajaran")

public class MataPelajaranEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "nama_mapel", length = 50, nullable = true)
    String nama_mapel;

    @Column(name = "kode_mapel", length = 50, nullable = true, unique = true)
    String kode_mapel;

    @Column(name = "tingkat", length = 50, nullable = true)
    String tingkat;

}
