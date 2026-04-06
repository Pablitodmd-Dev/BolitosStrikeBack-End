package com.example.webthymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webthymeleaf.entity.Promocion;
import com.example.webthymeleaf.repository.PromocionRepository;

@Service
public class PromocionService {

    @Autowired
    private PromocionRepository promocionRepository;

    public List<Promocion> getAllPromociones() {
        return promocionRepository.findAll();
    }

    public List<Promocion> getPromocionesActivas() {
        return promocionRepository.findByActivaTrue();
    }

    public List<Promocion> getPromocionesAlcanzables(int bolitos) {
        return promocionRepository.findByActivaTrueAndBolitosRequeridosLessThanEqual(bolitos);
    }

    public Promocion getPromocionById(Long id) {
        return promocionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promocion no encontrada"));
    }

    public Promocion createPromocion(Promocion promocion) {
        return promocionRepository.save(promocion);
    }

    public Promocion updatePromocion(Long id, Promocion promocionDetails) {
        Promocion promocion = getPromocionById(id);
        promocion.setNombre(promocionDetails.getNombre());
        promocion.setDescripcion(promocionDetails.getDescripcion());
        promocion.setBeneficio(promocionDetails.getBeneficio());
        promocion.setFechaInicio(promocionDetails.getFechaInicio());
        promocion.setActiva(promocionDetails.isActiva());
        promocion.setBolitosRequeridos(promocionDetails.getBolitosRequeridos());
        return promocionRepository.save(promocion);
    }

    public void deletePromocion(Long id) {
        getPromocionById(id);
        promocionRepository.deleteById(id);
    }
}