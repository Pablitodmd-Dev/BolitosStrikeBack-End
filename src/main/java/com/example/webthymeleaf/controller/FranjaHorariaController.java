package com.example.webthymeleaf.controller;

import com.example.webthymeleaf.entity.FranjaHoraria;
import com.example.webthymeleaf.service.FranjaHorariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/franjas")
public class FranjaHorariaController {

    @Autowired
    private FranjaHorariaService franjaHorariaService;

    @GetMapping
    public ResponseEntity<List<FranjaHoraria>> getAllFranjas() {
        return ResponseEntity.ok(franjaHorariaService.getAllFranjas());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<FranjaHoraria>> getFranjasDisponibles() {
        return ResponseEntity.ok(franjaHorariaService.getFranjasDisponibles());
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<FranjaHoraria>> getFranjasByFecha(@PathVariable LocalDate fecha) {
        return ResponseEntity.ok(franjaHorariaService.getFranjasByFecha(fecha));
    }

    @GetMapping("/disponibles/fecha/{fecha}")
    public ResponseEntity<List<FranjaHoraria>> getFranjasDisponiblesByFecha(@PathVariable LocalDate fecha) {
        return ResponseEntity.ok(franjaHorariaService.getFranjasDisponiblesByFecha(fecha));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FranjaHoraria> getFranjaById(@PathVariable Long id) {
        return ResponseEntity.ok(franjaHorariaService.getFranjaById(id));
    }

    @PostMapping
    public ResponseEntity<FranjaHoraria> createFranja(@RequestBody FranjaHoraria franja) {
        return ResponseEntity.ok(franjaHorariaService.createFranja(franja));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FranjaHoraria> updateFranja(@PathVariable Long id, @RequestBody FranjaHoraria franjaDetails) {
        return ResponseEntity.ok(franjaHorariaService.updateFranja(id, franjaDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFranja(@PathVariable Long id) {
        franjaHorariaService.deleteFranja(id);
        return ResponseEntity.noContent().build();
    }
}