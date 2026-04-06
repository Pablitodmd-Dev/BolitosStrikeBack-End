package com.example.webthymeleaf.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webthymeleaf.entity.Evento;
import com.example.webthymeleaf.repository.EventoRepository;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    public List<Evento> getEventosFuturos() {
        return eventoRepository.findByFechaAfter(LocalDateTime.now());
    }

    public List<Evento> getEventosByTipo(String tipo) {
        return eventoRepository.findByTipo(tipo);
    }

    public Evento getEventoById(Long id) {
        return eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }

    public Evento createEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public Evento updateEvento(Long id, Evento eventoDetails) {
        Evento evento = getEventoById(id);
        evento.setNombre(eventoDetails.getNombre());
        evento.setDescripcion(eventoDetails.getDescripcion());
        evento.setFecha(eventoDetails.getFecha());
        evento.setTipo(eventoDetails.getTipo());
        evento.setPremio(eventoDetails.getPremio());
        return eventoRepository.save(evento);
    }

    public void deleteEvento(Long id) {
        getEventoById(id);
        eventoRepository.deleteById(id);
    }
}