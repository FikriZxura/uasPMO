package com.zxura.uas.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zxura.uas.entity.BahanAjarEntity;
import com.zxura.uas.repository.BahanAjarRepository;

@Service
public class BahanAjarService {

    @Autowired
    private BahanAjarRepository repository;

    private final String uploadDir = "uploads/";

    public List<BahanAjarEntity> findAll() {
        return repository.findAll();
    }

    public Optional<BahanAjarEntity> findById(int id) {
        return repository.findById(id);
    }

    public BahanAjarEntity save(BahanAjarEntity bahanAjar, MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(uploadDir + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
            bahanAjar.setFile(fileName);
        }
        return repository.save(bahanAjar);
    }

    public BahanAjarEntity update(int id, BahanAjarEntity updatedBahanAjar, MultipartFile file) throws IOException {
        Optional<BahanAjarEntity> optionalBahanAjar = repository.findById(id);
        if (optionalBahanAjar.isPresent()) {
            BahanAjarEntity existingBahanAjar = optionalBahanAjar.get();
            existingBahanAjar.setJudul(updatedBahanAjar.getJudul());
            existingBahanAjar.setDeskripsi(updatedBahanAjar.getDeskripsi());
            existingBahanAjar.setTipe(updatedBahanAjar.getTipe());
            existingBahanAjar.setMataPelajaranEntity(updatedBahanAjar.getMataPelajaranEntity());

            if (!file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                Path path = Paths.get(uploadDir + fileName);
                Files.createDirectories(path.getParent());
                Files.write(path, file.getBytes());
                existingBahanAjar.setFile(fileName);
            }

            return repository.save(existingBahanAjar);
        } else {
            throw new RuntimeException("Bahan Ajar not found with id " + id);
        }
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}