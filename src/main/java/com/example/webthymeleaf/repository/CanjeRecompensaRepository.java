package com.example.webthymeleaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webthymeleaf.entity.CanjeRecompensa;

@Repository
public interface CanjeRecompensaRepository extends JpaRepository<CanjeRecompensa, Long> {
    List<CanjeRecompensa> findByUsuarioId(Long usuarioId);
}