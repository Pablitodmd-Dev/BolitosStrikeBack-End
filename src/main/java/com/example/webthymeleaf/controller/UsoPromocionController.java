package com.example.webthymeleaf.controller;

import com.example.webthymeleaf.entity.UsoPromocion;
import com.example.webthymeleaf.service.UsoPromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usos-promocion")
public class UsoPromocionController {

    @Autowired
    private UsoPromocionService usoPromocionService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<UsoPromocion>> getUsosByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(usoPromocionService.getUsosByUsuario(usuarioId));
    }

    @PostMapping
    public ResponseEntity<UsoPromocion> usarPromocion(@RequestBody UsoPromocion uso) {
        return ResponseEntity.ok(usoPromocionService.usarPromocion(uso));
    }
}