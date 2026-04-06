package com.example.webthymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webthymeleaf.entity.ParticipacionEvento;
import com.example.webthymeleaf.repository.ParticipacionEventoRepository;

@Service
public class ParticipacionEventoService {

    @Autowired
    private ParticipacionEventoRepository participacionEventoRepository;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UsuarioService usuarioService;

    public List<ParticipacionEvento> getParticipacionesByUsuario(Long usuarioId) {
        return participacionEventoRepository.findByUsuarioId(usuarioId);
    }

    public List<ParticipacionEvento> getParticipacionesByEvento(Long eventoId) {
        return participacionEventoRepository.findByEventoId(eventoId);
    }

    public ParticipacionEvento inscribirUsuario(ParticipacionEvento participacion) {
        usuarioService.getUsuarioById(participacion.getUsuario().getId());
        eventoService.getEventoById(participacion.getEvento().getId());

        if (participacionEventoRepository.findByUsuarioIdAndEventoId(
                participacion.getUsuario().getId(),
                participacion.getEvento().getId()).isPresent()) {
            throw new RuntimeException("El usuario ya está inscrito en este evento");
        }

        return participacionEventoRepository.save(participacion);
    }

    public void cancelarInscripcion(Long usuarioId, Long eventoId) {
        ParticipacionEvento participacion = participacionEventoRepository
                .findByUsuarioIdAndEventoId(usuarioId, eventoId)
                .orElseThrow(() -> new RuntimeException("Inscripcion no encontrada"));

        participacionEventoRepository.deleteById(participacion.getId());
    }
}