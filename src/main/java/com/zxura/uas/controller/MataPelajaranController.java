package com.zxura.uas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zxura.uas.entity.MataPelajaranEntity;
import com.zxura.uas.service.MataPelajaranService;

@RestController
@RequestMapping("/api/mata-pelajaran")
@CrossOrigin("*")
public class MataPelajaranController {
    @Autowired
    private MataPelajaranService mataPelajaranService;

    @GetMapping
    public ResponseEntity<List<MataPelajaranEntity>> getAll() {
        List<MataPelajaranEntity> dataList = mataPelajaranService.getAll();
        return new ResponseEntity<>(dataList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MataPelajaranEntity> create(@RequestBody MataPelajaranEntity guru) {
        MataPelajaranEntity saved = mataPelajaranService.create(guru);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MataPelajaranEntity> getById(@PathVariable int id) {
        MataPelajaranEntity data = mataPelajaranService.getById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MataPelajaranEntity> update(@PathVariable int id,
            @RequestBody MataPelajaranEntity mataPelajaranEntity) {
        MataPelajaranEntity saved = mataPelajaranService.update(id, mataPelajaranEntity);
        if (saved != null) {
            return ResponseEntity.ok(saved); // Mengembalikan status OK jika pembaruan berhasil
        } else {
            return ResponseEntity.notFound().build(); // Mengembalikan status not found jika entitas tidak ditemukan
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MataPelajaranEntity> delete(@PathVariable int id) {
        MataPelajaranEntity data = mataPelajaranService.getById(id);
        mataPelajaranService.delete(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}