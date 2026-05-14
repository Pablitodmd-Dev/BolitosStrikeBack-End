package com.example.webthymeleaf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webthymeleaf.entity.Valoracion;
import com.example.webthymeleaf.model.ValoracionRequestDTO;
import com.example.webthymeleaf.repository.ValoracionRepository;

@Service
public class ValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Autowired
    private ReservaService reservaService;
    
    public List<Valoracion> getAllValoraciones() {
        return valoracionRepository.findAll();
    }

    public Valoracion getValoracionById(Long id) {
        return valoracionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Valoracion no encontrada"));
    }
    
    public List<Valoracion> getValoracionesByUsuario(Long usuarioId) {
        return valoracionRepository.findByReservaUsuarioId(usuarioId);
    }
    
    public Valoracion createValoracion(Valoracion valoracion) {
        reservaService.getReservaById(valoracion.getReserva().getId());

        if (valoracionRepository.findByReservaId(valoracion.getReserva().getId()).isPresent()) {
            throw new RuntimeException("Esta reserva ya tiene una valoracion");
        }

        return valoracionRepository.save(valoracion);
    }
    
    public Valoracion createValoracionDTO(ValoracionRequestDTO dto) {
        Valoracion valoracion = new Valoracion();
        valoracion.setPuntuacion(dto.getPuntuacion());
        valoracion.setComentario(dto.getComentario());
        valoracion.setFecha(dto.getFecha());
        valoracion.setReserva(reservaService.getReservaById(dto.getReservaId()));
        return createValoracion(valoracion);
    }

    public void deleteValoracion(Long id) {
        valoracionRepository.deleteById(id);
    }
}