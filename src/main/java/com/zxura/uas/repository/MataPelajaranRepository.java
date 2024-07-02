package com.zxura.uas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zxura.uas.entity.MataPelajaranEntity;

public interface MataPelajaranRepository extends JpaRepository<MataPelajaranEntity, Integer> {
    MataPelajaranEntity findById(int id);

    default MataPelajaranEntity update(MataPelajaranEntity updatedData) {
        Integer id = updatedData.getId();
        if (id != null && existsById(id)) {
            return save(updatedData);
        }
        return null;
    }
}
