package com.example.webthymeleaf.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webthymeleaf.entity.FranjaHoraria;

@Repository
public interface FranjaHorariaRepository extends JpaRepository<FranjaHoraria, Long> {
    List<FranjaHoraria> findByDisponibleTrue();
    List<FranjaHoraria> findByFecha(LocalDate fecha);
    List<FranjaHoraria> findByFechaAndDisponibleTrue(LocalDate fecha);
}