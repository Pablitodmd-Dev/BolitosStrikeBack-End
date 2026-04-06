package com.example.webthymeleaf.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.webthymeleaf.entity.Pista;

@Repository
public interface PistaRepository extends JpaRepository<Pista, Long> {
    List<Pista> findByEstado(String estado);
}