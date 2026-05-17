package com.example.webthymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webthymeleaf.entity.Reserva;
import com.example.webthymeleaf.entity.Usuario;
import com.example.webthymeleaf.model.ReservaRequestDTO;
import com.example.webthymeleaf.repository.FranjaHorariaRepository;
import com.example.webthymeleaf.repository.PistaRepository;
import com.example.webthymeleaf.repository.ReservaRepository;
import com.example.webthymeleaf.repository.UsuarioRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private FranjaHorariaService franjaHorariaService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PistaRepository pistaRepository;

    @Autowired
    private FranjaHorariaRepository franjaHorariaRepository;
    
    @Autowired
    private UsuarioService usuarioService;
    
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

    public Reserva createReserva(ReservaRequestDTO dto) {
        Reserva reserva = new Reserva();
        reserva.setFecha(dto.getFecha());
        reserva.setNumPersonas(dto.getNumPersonas());
        reserva.setEstado(dto.getEstado() != null ? dto.getEstado() : "PENDIENTE");
        reserva.setMetodoPago(dto.getMetodoPago());
        reserva.setBolitosGenerados(dto.getBolitosGenerados());

        reserva.setUsuario(usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
        reserva.setPista(pistaRepository.findById(dto.getPistaId())
                .orElseThrow(() -> new RuntimeException("Pista no encontrada")));
        reserva.setFranjaHoraria(franjaHorariaRepository.findById(dto.getFranjaId())
                .orElseThrow(() -> new RuntimeException("Franja no encontrada")));

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
    
    public void completarReserva(Long id) {
        Reserva reserva = getReservaById(id);
        reserva.setEstado("COMPLETADA");
        
        Usuario usuario = reserva.getUsuario();
        usuario.setTotalBolitos(usuario.getTotalBolitos() + reserva.getBolitosGenerados());
        usuarioService.updateUsuario(usuario.getId(), usuario);
        
        reservaRepository.save(reserva);
    }
    
    public void cancelarReserva(Long id) {
        Reserva reserva = getReservaById(id);
        reserva.setEstado("CANCELADA");
        reservaRepository.save(reserva);
    }
}