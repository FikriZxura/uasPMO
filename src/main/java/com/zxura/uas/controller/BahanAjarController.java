package com.zxura.uas.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.zxura.uas.dto.BahanAjarDto;
import com.zxura.uas.entity.BahanAjarEntity;
import com.zxura.uas.entity.MataPelajaranEntity;
import com.zxura.uas.service.BahanAjarService;

@RestController
@RequestMapping("/api/bahan-ajar")
@CrossOrigin("*")
public class BahanAjarController {

    @Autowired
    private BahanAjarService service;

    @GetMapping
    public List<BahanAjarEntity> getAllBahanAjar() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BahanAjarDto> getBahanAjarById(@PathVariable int id) {
        Optional<BahanAjarEntity> bahanAjar = service.findById(id);
        return bahanAjar.map(this::convertToDto).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private BahanAjarDto convertToDto(BahanAjarEntity bahanAjar) {
        return new BahanAjarDto(
                bahanAjar.getId(),
                bahanAjar.getMataPelajaranEntity(),
                bahanAjar.getJudul(),
                bahanAjar.getDeskripsi(),
                bahanAjar.getTipe(),
                bahanAjar.getFile(),
                bahanAjar.getMataPelajaranEntity().getId() // Set id_mapel dari MataPelajaranEntity
        );
    }

    @PostMapping
    public ResponseEntity<BahanAjarEntity> createBahanAjar(@RequestParam("judul") String judul,
            @RequestParam("deskripsi") String deskripsi,
            @RequestParam("tipe") String tipe,
            @RequestParam("id_mapel") MataPelajaranEntity mataPelajaranEntity,
            @RequestParam("file") MultipartFile file) throws IOException {
        BahanAjarEntity bahanAjar = new BahanAjarEntity();
        bahanAjar.setJudul(judul);
        bahanAjar.setDeskripsi(deskripsi);
        bahanAjar.setTipe(tipe);
        bahanAjar.setMataPelajaranEntity(mataPelajaranEntity);
        BahanAjarEntity savedBahanAjar = service.save(bahanAjar, file);
        return ResponseEntity.ok(savedBahanAjar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BahanAjarEntity> updateBahanAjar(@PathVariable int id,
            @RequestParam("judul") String judul,
            @RequestParam("deskripsi") String deskripsi,
            @RequestParam("tipe") String tipe,
            @RequestParam("id_mapel") MataPelajaranEntity mataPelajaranEntity,
            @RequestParam("file") MultipartFile file) throws IOException {
        BahanAjarEntity updatedBahanAjar = new BahanAjarEntity();
        updatedBahanAjar.setJudul(judul);
        updatedBahanAjar.setDeskripsi(deskripsi);
        updatedBahanAjar.setTipe(tipe);
        updatedBahanAjar.setMataPelajaranEntity(mataPelajaranEntity);
        BahanAjarEntity savedBahanAjar = service.update(id, updatedBahanAjar, file);
        return ResponseEntity.ok(savedBahanAjar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBahanAjar(@PathVariable int id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            // Tentukan direktori penyimpanan
            String directory = "uploads/";
            // Tentukan path file
            Path filePath = Paths.get(directory, fileName);
            // Buat resource dari file
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                // Tentukan konten tipe
                String contentType = Files.probeContentType(filePath);
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                // Kembalikan file sebagai respons
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
