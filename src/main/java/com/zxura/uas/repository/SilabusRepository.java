package com.zxura.uas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zxura.uas.entity.SilabusEntity;

@Repository
public interface SilabusRepository extends JpaRepository<SilabusEntity, Integer> {
}
