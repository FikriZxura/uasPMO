package com.zxura.uas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zxura.uas.entity.BahanAjarEntity;

@Repository
public interface BahanAjarRepository extends JpaRepository<BahanAjarEntity, Integer> {
}
