package com.example.webthymeleaf.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.webthymeleaf.entity.FranjaHoraria;

@Repository
public interface FranjaHorariaRepository extends JpaRepository<FranjaHoraria, Long> {
	List<FranjaHoraria> findByDisponibleTrue();
}