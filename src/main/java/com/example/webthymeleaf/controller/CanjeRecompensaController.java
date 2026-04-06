package com.example.webthymeleaf.controller;

import com.example.webthymeleaf.entity.CanjeRecompensa;
import com.example.webthymeleaf.service.CanjeRecompensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/canjes")
public class CanjeRecompensaController {

    @Autowired
    private CanjeRecompensaService canjeRecompensaService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<CanjeRecompensa>> getCanjesByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(canjeRecompensaService.getCanjesByUsuario(usuarioId));
    }

    @PostMapping
    public ResponseEntity<CanjeRecompensa> canjearRecompensa(@RequestBody CanjeRecompensa canje) {
        return ResponseEntity.ok(canjeRecompensaService.canjearRecompensa(canje));
    }
}