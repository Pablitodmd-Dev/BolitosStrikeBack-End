package com.example.webthymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webthymeleaf.entity.Recompensa;
import com.example.webthymeleaf.repository.RecompensaRepository;

@Service
public class RecompensaService {

    @Autowired
    private RecompensaRepository recompensaRepository;

    public List<Recompensa> getAllRecompensas() {
        return recompensaRepository.findAll();
    }

    public List<Recompensa> getRecompensasDisponibles() {
        return recompensaRepository.findByStockGreaterThan(0);
    }

    public Recompensa getRecompensaById(Long id) {
        return recompensaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recompensa no encontrada"));
    }

    public Recompensa createRecompensa(Recompensa recompensa) {
        return recompensaRepository.save(recompensa);
    }

    public Recompensa updateRecompensa(Long id, Recompensa recompensaDetails) {
        Recompensa recompensa = getRecompensaById(id);
        recompensa.setNombre(recompensaDetails.getNombre());
        recompensa.setCoste(recompensaDetails.getCoste());
        recompensa.setStock(recompensaDetails.getStock());
        return recompensaRepository.save(recompensa);
    }

    public void deleteRecompensa(Long id) {
        getRecompensaById(id);
        recompensaRepository.deleteById(id);
    }
}