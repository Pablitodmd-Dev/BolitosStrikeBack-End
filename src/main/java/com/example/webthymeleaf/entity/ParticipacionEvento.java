package com.example.webthymeleaf.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ParticipacionEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    private LocalDateTime fechaInscripcion;

    private String resultado;

	public ParticipacionEvento() {
		super();
	}

	public ParticipacionEvento(Long id, Usuario usuario, Evento evento, LocalDateTime fechaInscripcion,
			String resultado) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.evento = evento;
		this.fechaInscripcion = fechaInscripcion;
		this.resultado = resultado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public LocalDateTime getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(LocalDateTime fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
    
    
}