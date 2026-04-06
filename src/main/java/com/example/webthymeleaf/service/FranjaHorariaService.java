package com.example.webthymeleaf.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webthymeleaf.entity.FranjaHoraria;
import com.example.webthymeleaf.repository.FranjaHorariaRepository;

@Service
public class FranjaHorariaService {

    @Autowired
    private FranjaHorariaRepository franjaHorariaRepository;

    public List<FranjaHoraria> getAllFranjas() {
        return franjaHorariaRepository.findAll();
    }

    public List<FranjaHoraria> getFranjasDisponibles() {
        return franjaHorariaRepository.findByDisponibleTrue();
    }

    public List<FranjaHoraria> getFranjasByFecha(LocalDate fecha) {
        return franjaHorariaRepository.findByFecha(fecha);
    }

    public List<FranjaHoraria> getFranjasDisponiblesByFecha(LocalDate fecha) {
        return franjaHorariaRepository.findByFechaAndDisponibleTrue(fecha);
    }

    public FranjaHoraria getFranjaById(Long id) {
        return franjaHorariaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Franja horaria no encontrada"));
    }

    public FranjaHoraria createFranja(FranjaHoraria franja) {
        return franjaHorariaRepository.save(franja);
    }

    public FranjaHoraria updateFranja(Long id, FranjaHoraria franjaDetails) {
        FranjaHoraria franja = getFranjaById(id);
        franja.setFecha(franjaDetails.getFecha());
        franja.setHoraInicio(franjaDetails.getHoraInicio());
        franja.setHoraFin(franjaDetails.getHoraFin());
        franja.setDisponible(franjaDetails.isDisponible());
        return franjaHorariaRepository.save(franja);
    }

    public void deleteFranja(Long id) {
        getFranjaById(id);
        franjaHorariaRepository.deleteById(id);
    }
}