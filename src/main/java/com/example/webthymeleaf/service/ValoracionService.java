package com.example.webthymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webthymeleaf.entity.Valoracion;
import com.example.webthymeleaf.repository.ValoracionRepository;

@Service
public class ValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Autowired
    private ReservaService reservaService;

    public List<Valoracion> getAllValoraciones() {
        return valoracionRepository.findAll();
    }

    public Valoracion getValoracionById(Long id) {
        return valoracionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Valoracion no encontrada"));
    }

    public Valoracion createValoracion(Valoracion valoracion) {
        reservaService.getReservaById(valoracion.getReserva().getId());

        if (valoracionRepository.findByReservaId(valoracion.getReserva().getId()).isPresent()) {
            throw new RuntimeException("Esta reserva ya tiene una valoracion");
        }

        return valoracionRepository.save(valoracion);
    }

    public void deleteValoracion(Long id) {
        getValoracionById(id);
        valoracionRepository.deleteById(id);
    }
}