package com.example.webthymeleaf.controller;

import com.example.webthymeleaf.entity.Promocion;
import com.example.webthymeleaf.service.PromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promociones")
public class PromocionController {

    @Autowired
    private PromocionService promocionService;

    @GetMapping
    public ResponseEntity<List<Promocion>> getAllPromociones() {
        return ResponseEntity.ok(promocionService.getAllPromociones());
    }

    @GetMapping("/activas")
    public ResponseEntity<List<Promocion>> getPromocionesActivas() {
        return ResponseEntity.ok(promocionService.getPromocionesActivas());
    }

    @GetMapping("/alcanzables/{bolitos}")
    public ResponseEntity<List<Promocion>> getPromocionesAlcanzables(@PathVariable int bolitos) {
        return ResponseEntity.ok(promocionService.getPromocionesAlcanzables(bolitos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promocion> getPromocionById(@PathVariable Long id) {
        return ResponseEntity.ok(promocionService.getPromocionById(id));
    }

    @PostMapping
    public ResponseEntity<Promocion> createPromocion(@RequestBody Promocion promocion) {
        return ResponseEntity.ok(promocionService.createPromocion(promocion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promocion> updatePromocion(@PathVariable Long id, @RequestBody Promocion promocionDetails) {
        return ResponseEntity.ok(promocionService.updatePromocion(id, promocionDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromocion(@PathVariable Long id) {
        promocionService.deletePromocion(id);
        return ResponseEntity.noContent().build();
    }
}