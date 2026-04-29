package com.example.webthymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webthymeleaf.entity.Reserva;
import com.example.webthymeleaf.model.ReservaRequestDTO;
import com.example.webthymeleaf.repository.FranjaHorariaRepository;
import com.example.webthymeleaf.repository.PistaRepository;
import com.example.webthymeleaf.repository.UsuarioRepository;
import com.example.webthymeleaf.service.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;
    
    @Autowired
    private com.example.webthymeleaf.repository.UsuarioRepository usuarioRepository;

    @Autowired
    private com.example.webthymeleaf.repository.PistaRepository pistaRepository;

    @Autowired
    private com.example.webthymeleaf.repository.FranjaHorariaRepository franjaHorariaRepository;

    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReservas() {
        return ResponseEntity.ok(reservaService.getAllReservas());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Reserva>> getReservasByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(reservaService.getReservasByUsuario(usuarioId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Reserva>> getReservasByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(reservaService.getReservasByEstado(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReservaById(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.getReservaById(id));
    }

    @PostMapping
    public ResponseEntity<Reserva> createReserva(@RequestBody ReservaRequestDTO dto) {
        Reserva reserva = new Reserva();
        reserva.setFecha(dto.getFecha());
        reserva.setNumPersonas(dto.getNumPersonas());
        reserva.setEstado(dto.getEstado() != null ? dto.getEstado() : "CONFIRMADA");
        reserva.setMetodoPago(dto.getMetodoPago());
        reserva.setBolitosGenerados(dto.getBolitosGenerados());

        reserva.setUsuario(usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
        reserva.setPista(pistaRepository.findById(dto.getPistaId())
                .orElseThrow(() -> new RuntimeException("Pista no encontrada")));
        reserva.setFranjaHoraria(franjaHorariaRepository.findById(dto.getFranjaId())
                .orElseThrow(() -> new RuntimeException("Franja no encontrada")));

        return ResponseEntity.ok(reservaService.createReserva(reserva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody Reserva reservaDetails) {
        return ResponseEntity.ok(reservaService.updateReserva(id, reservaDetails));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        return ResponseEntity.noContent().build();
    }
}