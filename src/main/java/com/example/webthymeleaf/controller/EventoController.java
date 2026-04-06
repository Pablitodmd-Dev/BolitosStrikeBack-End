package com.example.webthymeleaf.controller;

import com.example.webthymeleaf.entity.Evento;
import com.example.webthymeleaf.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventos() {
        return ResponseEntity.ok(eventoService.getAllEventos());
    }

    @GetMapping("/futuros")
    public ResponseEntity<List<Evento>> getEventosFuturos() {
        return ResponseEntity.ok(eventoService.getEventosFuturos());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Evento>> getEventosByTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(eventoService.getEventosByTipo(tipo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        return ResponseEntity.ok(eventoService.getEventoById(id));
    }

    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        return ResponseEntity.ok(eventoService.createEvento(evento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Long id, @RequestBody Evento eventoDetails) {
        return ResponseEntity.ok(eventoService.updateEvento(id, eventoDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }
}