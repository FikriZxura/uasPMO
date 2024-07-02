package com.zxura.uas.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zxura.uas.entity.PrestasiAkademikEntity;
import com.zxura.uas.entity.MataPelajaranEntity;
import com.zxura.uas.service.PrestasiAkademikService;

@RestController
@RequestMapping("/api/prestasi")
public class PrestasiAkademikController {

    @Autowired
    private PrestasiAkademikService service;

    @GetMapping
    public List<PrestasiAkademikEntity> getAllPrestasi() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestasiAkademikEntity> getPrestasiById(@PathVariable int id) {
        Optional<PrestasiAkademikEntity> prestasi = service.findById(id);
        if (prestasi.isPresent()) {
            return ResponseEntity.ok(prestasi.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PrestasiAkademikEntity> createPrestasi(@RequestParam("id_siswa") int idSiswa,
            @RequestParam("tanggal") Date tanggal,
            @RequestParam("jenis_prestasi") String jenisPrestasi,
            @RequestParam("deskripsi_nilai") String deskripsiNilai,
            @RequestParam("id_mapel") int idMapel) {
        Optional<MataPelajaranEntity> optionalMapel = service.findMataPelajaranById(idMapel);
        if (optionalMapel.isPresent()) {
            PrestasiAkademikEntity prestasi = new PrestasiAkademikEntity();
            prestasi.setIdSiswa(idSiswa);
            prestasi.setTanggal(tanggal);
            prestasi.setJenisPrestasi(jenisPrestasi);
            prestasi.setDeskripsiNilai(deskripsiNilai);
            prestasi.setMataPelajaranEntity(optionalMapel.get());
            return ResponseEntity.ok(service.save(prestasi));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrestasiAkademikEntity> updatePrestasi(@PathVariable int id,
            @RequestParam("id_siswa") int idSiswa,
            @RequestParam("tanggal") Date tanggal,
            @RequestParam("jenis_prestasi") String jenisPrestasi,
            @RequestParam("deskripsi_nilai") String deskripsiNilai,
            @RequestParam("id_mapel") int idMapel) {
        Optional<MataPelajaranEntity> optionalMapel = service.findMataPelajaranById(idMapel);
        if (optionalMapel.isPresent()) {
            PrestasiAkademikEntity updatedPrestasi = new PrestasiAkademikEntity();
            updatedPrestasi.setIdSiswa(idSiswa);
            updatedPrestasi.setTanggal(tanggal);
            updatedPrestasi.setJenisPrestasi(jenisPrestasi);
            updatedPrestasi.setDeskripsiNilai(deskripsiNilai);
            updatedPrestasi.setMataPelajaranEntity(optionalMapel.get());
            try {
                PrestasiAkademikEntity updated = service.update(id, updatedPrestasi);
                return ResponseEntity.ok(updated);
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestasi(@PathVariable int id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
