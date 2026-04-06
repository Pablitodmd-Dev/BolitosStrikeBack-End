package com.example.webthymeleaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webthymeleaf.entity.HistorialBolitos;

@Repository
public interface HistorialBolitosRepository extends JpaRepository<HistorialBolitos, Long> {
    List<HistorialBolitos> findByUsuarioId(Long usuarioId);
    List<HistorialBolitos> findByUsuarioIdAndTipoMovimiento(Long usuarioId, String tipoMovimiento);
}