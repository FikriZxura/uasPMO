package com.zxura.uas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxura.uas.entity.PrestasiAkademikEntity;
import com.zxura.uas.entity.MataPelajaranEntity;
import com.zxura.uas.repository.PrestasiAkademikRepository;
import com.zxura.uas.repository.MataPelajaranRepository;

@Service
public class PrestasiAkademikService {

    @Autowired
    private PrestasiAkademikRepository prestasiRepository;

    @Autowired
    private MataPelajaranRepository matapelajranrepo;

    public List<PrestasiAkademikEntity> findAll() {
        return prestasiRepository.findAll();
    }

    public Optional<PrestasiAkademikEntity> findById(int id) {
        return prestasiRepository.findById(id);
    }

    public PrestasiAkademikEntity save(PrestasiAkademikEntity prestasi) {
        return prestasiRepository.save(prestasi);
    }

    public PrestasiAkademikEntity update(int id, PrestasiAkademikEntity updatedPrestasi) {
        Optional<PrestasiAkademikEntity> optionalPrestasi = prestasiRepository.findById(id);
        if (optionalPrestasi.isPresent()) {
            PrestasiAkademikEntity existingPrestasi = optionalPrestasi.get();
            existingPrestasi.setIdSiswa(updatedPrestasi.getIdSiswa());
            existingPrestasi.setTanggal(updatedPrestasi.getTanggal());
            existingPrestasi.setJenisPrestasi(updatedPrestasi.getJenisPrestasi());
            existingPrestasi.setDeskripsiNilai(updatedPrestasi.getDeskripsiNilai());
            existingPrestasi.setMataPelajaranEntity(updatedPrestasi.getMataPelajaranEntity());
            return prestasiRepository.save(existingPrestasi);
        } else {
            throw new RuntimeException("Prestasi not found with id " + id);
        }
    }

    public void deleteById(int id) {
        prestasiRepository.deleteById(id);
    }

    public Optional<MataPelajaranEntity> findMataPelajaranById(int idMapel) {
        return Optional.ofNullable(matapelajranrepo.findById(idMapel));
    }
}
