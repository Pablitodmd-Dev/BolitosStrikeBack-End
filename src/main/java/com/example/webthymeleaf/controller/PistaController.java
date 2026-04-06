package com.example.webthymeleaf.controller;

import com.example.webthymeleaf.entity.Pista;
import com.example.webthymeleaf.service.PistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pistas")
public class PistaController {

    @Autowired
    private PistaService pistaService;

    @GetMapping
    public ResponseEntity<List<Pista>> getAllPistas() {
        return ResponseEntity.ok(pistaService.getAllPistas());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Pista>> getPistasDisponibles() {
        return ResponseEntity.ok(pistaService.getPistasDisponibles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pista> getPistaById(@PathVariable Long id) {
        return ResponseEntity.ok(pistaService.getPistaById(id));
    }

    @PostMapping
    public ResponseEntity<Pista> createPista(@RequestBody Pista pista) {
        return ResponseEntity.ok(pistaService.createPista(pista));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pista> updatePista(@PathVariable Long id, @RequestBody Pista pistaDetails) {
        return ResponseEntity.ok(pistaService.updatePista(id, pistaDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePista(@PathVariable Long id) {
        pistaService.deletePista(id);
        return ResponseEntity.noContent().build();
    }
}