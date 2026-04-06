package com.example.webthymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webthymeleaf.entity.Pista;
import com.example.webthymeleaf.repository.PistaRepository;

@Service
public class PistaService {

    @Autowired
    private PistaRepository pistaRepository;

    public List<Pista> getAllPistas() {
        return pistaRepository.findAll();
    }

    public List<Pista> getPistasDisponibles() {
        return pistaRepository.findByEstado("DISPONIBLE");
    }

    public Pista getPistaById(Long id) {
        return pistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pista no encontrada"));
    }

    public Pista createPista(Pista pista) {
        return pistaRepository.save(pista);
    }

    public Pista updatePista(Long id, Pista pistaDetails) {
        Pista pista = getPistaById(id);
        pista.setNumPista(pistaDetails.getNumPista());
        pista.setEstado(pistaDetails.getEstado());
        pista.setDescripcion(pistaDetails.getDescripcion());
        return pistaRepository.save(pista);
    }

    public void deletePista(Long id) {
        getPistaById(id);
        pistaRepository.deleteById(id);
    }
}