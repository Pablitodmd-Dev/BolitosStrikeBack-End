package com.example.webthymeleaf.controller;

import com.example.webthymeleaf.entity.Notificacion;
import com.example.webthymeleaf.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Notificacion>> getNotificacionesByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(notificacionService.getNotificacionesByUsuario(usuarioId));
    }

    @GetMapping("/usuario/{usuarioId}/noleidas")
    public ResponseEntity<List<Notificacion>> getNotificacionesNoLeidas(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(notificacionService.getNotificacionesNoLeidas(usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> getNotificacionById(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.getNotificacionById(id));
    }

    @PostMapping
    public ResponseEntity<Notificacion> createNotificacion(@RequestBody Notificacion notificacion) {
        return ResponseEntity.ok(notificacionService.createNotificacion(notificacion));
    }

    @PutMapping("/{id}/leer")
    public ResponseEntity<Notificacion> marcarComoLeida(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.marcarComoLeida(id));
    }

    @PutMapping("/usuario/{usuarioId}/leer-todas")
    public ResponseEntity<Void> marcarTodasComoLeidas(@PathVariable Long usuarioId) {
        notificacionService.marcarTodasComoLeidas(usuarioId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificacion(@PathVariable Long id) {
        notificacionService.deleteNotificacion(id);
        return ResponseEntity.noContent().build();
    }
}