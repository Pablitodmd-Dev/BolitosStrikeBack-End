package com.example.webthymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webthymeleaf.entity.FranjaHoraria;
import com.example.webthymeleaf.entity.Reserva;
import com.example.webthymeleaf.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private FranjaHorariaService franjaHorariaService;

    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    public List<Reserva> getReservasByUsuario(Long usuarioId) {
        return reservaRepository.findByUsuarioId(usuarioId);
    }

    public List<Reserva> getReservasByEstado(String estado) {
        return reservaRepository.findByEstado(estado);
    }

    public Reserva getReservaById(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    public Reserva createReserva(Reserva reserva) {
        FranjaHoraria franja = franjaHorariaService.getFranjaById(reserva.getFranjaHoraria().getId());
        franja.setDisponible(false);
        franjaHorariaService.updateFranja(franja.getId(), franja);
        reserva.setFranjaHoraria(franja);
        return reservaRepository.save(reserva);
    }

    public Reserva updateReserva(Long id, Reserva reservaDetails) {
        Reserva reserva = getReservaById(id);
        reserva.setFecha(reservaDetails.getFecha());
        reserva.setNumPersonas(reservaDetails.getNumPersonas());
        reserva.setEstado(reservaDetails.getEstado());
        reserva.setMetodoPago(reservaDetails.getMetodoPago());
        return reservaRepository.save(reserva);
    }

    public void cancelarReserva(Long id) {
        Reserva reserva = getReservaById(id);
        reserva.setEstado("CANCELADA");
        FranjaHoraria franja = reserva.getFranjaHoraria();
        franja.setDisponible(true);
        franjaHorariaService.updateFranja(franja.getId(), franja);
        reservaRepository.save(reserva);
    }
}