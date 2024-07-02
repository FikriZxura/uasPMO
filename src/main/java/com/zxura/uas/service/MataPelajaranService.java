package com.zxura.uas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxura.uas.entity.MataPelajaranEntity;
import com.zxura.uas.repository.MataPelajaranRepository;

@Service
public class MataPelajaranService {
    @Autowired
    private MataPelajaranRepository mataPelajaranRepository;

    public List<MataPelajaranEntity> getAll() {
        return mataPelajaranRepository.findAll();
    }

    public MataPelajaranEntity create(MataPelajaranEntity data) {
        return mataPelajaranRepository.save(data);
    }

    public MataPelajaranEntity update(int id, MataPelajaranEntity updatedData) {
        Optional<MataPelajaranEntity> optionalData = Optional.of(mataPelajaranRepository.findById(id));
        if (optionalData.isPresent()) {
            MataPelajaranEntity existingData = optionalData.get();
            existingData.setNama_mapel(updatedData.getNama_mapel());
            existingData.setKode_mapel(updatedData.getKode_mapel());
            existingData.setTingkat(updatedData.getTingkat());
            // Lakukan update terhadap properti lain yang perlu diperbarui
            return mataPelajaranRepository.save(existingData);
        } else {
            return updatedData; // Kembalikan null jika data tidak ditemukan
        }
    }

    public MataPelajaranEntity getById(int id) {
        return mataPelajaranRepository.findById(id);
    }

    public void delete(int id) {
        mataPelajaranRepository.deleteById(id);
    }

    public List<MataPelajaranEntity> getByGuruId(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'getByGuruId'");
    }
}
