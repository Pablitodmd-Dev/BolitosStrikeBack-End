package com.example.webthymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webthymeleaf.entity.CanjeRecompensa;
import com.example.webthymeleaf.model.CanjeRecompensaRequestDTO;
import com.example.webthymeleaf.service.CanjeRecompensaService;

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
    public ResponseEntity<CanjeRecompensa> canjearRecompensa(@RequestBody CanjeRecompensaRequestDTO dto) {
        return ResponseEntity.ok(canjeRecompensaService.canjearRecompensaDTO(dto));
    }
}