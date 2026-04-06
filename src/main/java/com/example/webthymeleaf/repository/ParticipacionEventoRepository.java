package com.example.webthymeleaf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webthymeleaf.entity.ParticipacionEvento;

@Repository
public interface ParticipacionEventoRepository extends JpaRepository<ParticipacionEvento, Long> {
    List<ParticipacionEvento> findByUsuarioId(Long usuarioId);
    List<ParticipacionEvento> findByEventoId(Long eventoId);
    Optional<ParticipacionEvento> findByUsuarioIdAndEventoId(Long usuarioId, Long eventoId);
}