package com.zxura.uas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zxura.uas.entity.SilabusEntity;
import com.zxura.uas.entity.MataPelajaranEntity;
import com.zxura.uas.service.SilabusService;

@RestController
@RequestMapping("/api/silabus")
@CrossOrigin("*")
public class SilabusController {

    @Autowired
    private SilabusService service;

    @GetMapping
    public List<SilabusEntity> getAllSilabus() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SilabusEntity> getSilabusById(@PathVariable int id) {
        Optional<SilabusEntity> silabus = service.findById(id);
        return silabus.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SilabusEntity> createSilabus(@RequestParam("judul") String judul,
            @RequestParam("deskripsi") String deskripsi,
            @RequestParam("tujuan") String tujuan,
            @RequestParam("materi") String materi,
            @RequestParam("metode") String metode,
            @RequestParam("evaluasi") String evaluasi,
            @RequestParam("id_mapel") int idMapel) {
        Optional<MataPelajaranEntity> optionalMapel = service.findMataPelajaranById(idMapel);
        if (optionalMapel.isPresent()) {
            SilabusEntity silabus = new SilabusEntity();
            silabus.setJudul(judul);
            silabus.setDeskripsi(deskripsi);
            silabus.setTujuan(tujuan);
            silabus.setMateri(materi);
            silabus.setMetode(metode);
            silabus.setEvaluasi(evaluasi);
            silabus.setMataPelajaranEntity(optionalMapel.get());
            SilabusEntity savedSilabus = service.save(silabus);
            return ResponseEntity.ok(savedSilabus);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SilabusEntity> updateSilabus(@PathVariable int id,
            @RequestParam("judul") String judul,
            @RequestParam("deskripsi") String deskripsi,
            @RequestParam("tujuan") String tujuan,
            @RequestParam("materi") String materi,
            @RequestParam("metode") String metode,
            @RequestParam("evaluasi") String evaluasi,
            @RequestParam("id_mapel") int idMapel) {
        Optional<MataPelajaranEntity> optionalMapel = service.findMataPelajaranById(idMapel);
        if (optionalMapel.isPresent()) {
            SilabusEntity updatedSilabus = new SilabusEntity();
            updatedSilabus.setJudul(judul);
            updatedSilabus.setDeskripsi(deskripsi);
            updatedSilabus.setTujuan(tujuan);
            updatedSilabus.setMateri(materi);
            updatedSilabus.setMetode(metode);
            updatedSilabus.setEvaluasi(evaluasi);
            updatedSilabus.setMataPelajaranEntity(optionalMapel.get());
            SilabusEntity savedSilabus = service.update(id, updatedSilabus);
            return ResponseEntity.ok(savedSilabus);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSilabus(@PathVariable int id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
