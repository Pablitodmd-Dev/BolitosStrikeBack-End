package com.example.webthymeleaf.controller;

import com.example.webthymeleaf.entity.HistorialBolitos;
import com.example.webthymeleaf.service.HistorialBolitosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial-bolitos")
public class HistorialBolitosController {

    @Autowired
    private HistorialBolitosService historialBolitosService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<HistorialBolitos>> getHistorialByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(historialBolitosService.getHistorialByUsuario(usuarioId));
    }

    @GetMapping("/usuario/{usuarioId}/tipo/{tipo}")
    public ResponseEntity<List<HistorialBolitos>> getHistorialByTipo(
            @PathVariable Long usuarioId,
            @PathVariable String tipo) {
        return ResponseEntity.ok(historialBolitosService.getHistorialByTipo(usuarioId, tipo));
    }

    @PostMapping
    public ResponseEntity<HistorialBolitos> registrarMovimiento(@RequestBody HistorialBolitos movimiento) {
        return ResponseEntity.ok(historialBolitosService.registrarMovimiento(movimiento));
    }
}