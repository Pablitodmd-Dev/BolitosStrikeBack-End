package com.example.webthymeleaf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.webthymeleaf.entity.Promocion;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Long> {
    List<Promocion> findByActivaTrue();
    List<Promocion> findByActivaTrueAndBolitosRequeridosLessThanEqual(int bolitos);
}