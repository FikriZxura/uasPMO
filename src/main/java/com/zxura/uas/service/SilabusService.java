package com.zxura.uas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxura.uas.entity.MataPelajaranEntity;
import com.zxura.uas.entity.SilabusEntity;
import com.zxura.uas.repository.MataPelajaranRepository;
import com.zxura.uas.repository.SilabusRepository;

@Service
public class SilabusService {

    @Autowired
    private SilabusRepository repository;
    @Autowired
    private MataPelajaranRepository mataPelajaranRepository;

    public List<SilabusEntity> findAll() {
        return repository.findAll();
    }

    public Optional<SilabusEntity> findById(int id) {
        return repository.findById(id);
    }

    public SilabusEntity save(SilabusEntity silabus) {
        return repository.save(silabus);
    }

    public SilabusEntity update(int id, SilabusEntity updatedSilabus) {
        Optional<SilabusEntity> optionalSilabus = repository.findById(id);
        if (optionalSilabus.isPresent()) {
            SilabusEntity existingSilabus = optionalSilabus.get();
            existingSilabus.setJudul(updatedSilabus.getJudul());
            existingSilabus.setDeskripsi(updatedSilabus.getDeskripsi());
            existingSilabus.setTujuan(updatedSilabus.getTujuan());
            existingSilabus.setMateri(updatedSilabus.getMateri());
            existingSilabus.setMetode(updatedSilabus.getMetode());
            existingSilabus.setEvaluasi(updatedSilabus.getEvaluasi());
            existingSilabus.setMataPelajaranEntity(updatedSilabus.getMataPelajaranEntity());
            return repository.save(existingSilabus);
        } else {
            throw new RuntimeException("Silabus not found with id " + id);
        }
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public Optional<MataPelajaranEntity> findMataPelajaranById(int idMapel) {
        return Optional.ofNullable(mataPelajaranRepository.findById(idMapel));
    }
}
