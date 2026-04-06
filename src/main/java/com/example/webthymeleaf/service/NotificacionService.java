package com.example.webthymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webthymeleaf.entity.Notificacion;
import com.example.webthymeleaf.repository.NotificacionRepository;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> getNotificacionesByUsuario(Long usuarioId) {
        return notificacionRepository.findByUsuarioId(usuarioId);
    }

    public List<Notificacion> getNotificacionesNoLeidas(Long usuarioId) {
        return notificacionRepository.findByUsuarioIdAndLeidaFalse(usuarioId);
    }

    public Notificacion getNotificacionById(Long id) {
        return notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificacion no encontrada"));
    }

    public Notificacion createNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public Notificacion marcarComoLeida(Long id) {
        Notificacion notificacion = getNotificacionById(id);
        notificacion.setLeida(true);
        return notificacionRepository.save(notificacion);
    }

    public void marcarTodasComoLeidas(Long usuarioId) {
        notificacionRepository.findByUsuarioIdAndLeidaFalse(usuarioId)
                .forEach(n -> {
                    n.setLeida(true);
                    notificacionRepository.save(n);
                });
    }

    public void deleteNotificacion(Long id) {
        getNotificacionById(id);
        notificacionRepository.deleteById(id);
    }
}