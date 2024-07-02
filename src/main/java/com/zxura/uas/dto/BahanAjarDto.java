package com.zxura.uas.dto;

import com.zxura.uas.entity.MataPelajaranEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BahanAjarDto {
    private int id;
    private MataPelajaranEntity mataPelajaranEntity;
    private String judul;
    private String deskripsi;
    private String tipe;
    private String file;
    private int id_mapel;
}
