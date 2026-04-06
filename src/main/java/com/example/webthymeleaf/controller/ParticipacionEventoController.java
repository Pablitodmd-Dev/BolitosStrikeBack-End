package com.example.webthymeleaf.controller;

import com.example.webthymeleaf.entity.ParticipacionEvento;
import com.example.webthymeleaf.service.ParticipacionEventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participaciones")
public class ParticipacionEventoController {

    @Autowired
    private ParticipacionEventoService participacionEventoService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ParticipacionEvento>> getParticipacionesByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(participacionEventoService.getParticipacionesByUsuario(usuarioId));
    }

    @GetMapping("/evento/{eventoId}")
    public ResponseEntity<List<ParticipacionEvento>> getParticipacionesByEvento(@PathVariable Long eventoId) {
        return ResponseEntity.ok(participacionEventoService.getParticipacionesByEvento(eventoId));
    }

    @PostMapping
    public ResponseEntity<ParticipacionEvento> inscribirUsuario(@RequestBody ParticipacionEvento participacion) {
        return ResponseEntity.ok(participacionEventoService.inscribirUsuario(participacion));
    }

    @DeleteMapping("/usuario/{usuarioId}/evento/{eventoId}")
    public ResponseEntity<Void> cancelarInscripcion(
            @PathVariable Long usuarioId,
            @PathVariable Long eventoId) {
        participacionEventoService.cancelarInscripcion(usuarioId, eventoId);
        return ResponseEntity.noContent().build();
    }
}