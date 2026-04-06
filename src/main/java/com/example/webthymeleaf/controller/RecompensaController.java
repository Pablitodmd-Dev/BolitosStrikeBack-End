package com.example.webthymeleaf.controller;

import com.example.webthymeleaf.entity.Recompensa;
import com.example.webthymeleaf.service.RecompensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recompensas")
public class RecompensaController {

    @Autowired
    private RecompensaService recompensaService;

    @GetMapping
    public ResponseEntity<List<Recompensa>> getAllRecompensas() {
        return ResponseEntity.ok(recompensaService.getAllRecompensas());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Recompensa>> getRecompensasDisponibles() {
        return ResponseEntity.ok(recompensaService.getRecompensasDisponibles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recompensa> getRecompensaById(@PathVariable Long id) {
        return ResponseEntity.ok(recompensaService.getRecompensaById(id));
    }

    @PostMapping
    public ResponseEntity<Recompensa> createRecompensa(@RequestBody Recompensa recompensa) {
        return ResponseEntity.ok(recompensaService.createRecompensa(recompensa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recompensa> updateRecompensa(@PathVariable Long id, @RequestBody Recompensa recompensaDetails) {
        return ResponseEntity.ok(recompensaService.updateRecompensa(id, recompensaDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecompensa(@PathVariable Long id) {
        recompensaService.deleteRecompensa(id);
        return ResponseEntity.noContent().build();
    }
}