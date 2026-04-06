package com.example.webthymeleaf.controller;

import com.example.webthymeleaf.entity.Valoracion;
import com.example.webthymeleaf.service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/valoraciones")
public class ValoracionController {

    @Autowired
    private ValoracionService valoracionService;

    @GetMapping
    public ResponseEntity<List<Valoracion>> getAllValoraciones() {
        return ResponseEntity.ok(valoracionService.getAllValoraciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Valoracion> getValoracionById(@PathVariable Long id) {
        return ResponseEntity.ok(valoracionService.getValoracionById(id));
    }

    @PostMapping
    public ResponseEntity<Valoracion> createValoracion(@RequestBody Valoracion valoracion) {
        return ResponseEntity.ok(valoracionService.createValoracion(valoracion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteValoracion(@PathVariable Long id) {
        valoracionService.deleteValoracion(id);
        return ResponseEntity.noContent().build();
    }
}