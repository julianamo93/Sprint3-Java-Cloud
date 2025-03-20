package com.smartooth.repository;

import com.smartooth.model.SistemaPontos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SistemaPontosRepository extends JpaRepository<SistemaPontos, Long> {

}

