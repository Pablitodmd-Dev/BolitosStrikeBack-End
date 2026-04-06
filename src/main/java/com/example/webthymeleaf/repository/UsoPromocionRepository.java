package com.example.webthymeleaf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webthymeleaf.entity.UsoPromocion;

@Repository
public interface UsoPromocionRepository extends JpaRepository<UsoPromocion, Long> {
    List<UsoPromocion> findByUsuarioId(Long usuarioId);
    Optional<UsoPromocion> findByUsuarioIdAndPromocionId(Long usuarioId, Long promocionId);
}