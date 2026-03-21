package com.example.webthymeleaf.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private String titulo;

    private String mensaje;

    private LocalDateTime fechaEnvio;

    private boolean leida;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = true)
    private Evento evento;

	public Notificacion() {
		super();
	}

	public Notificacion(Long id, String tipo, String titulo, String mensaje, LocalDateTime fechaEnvio, boolean leida,
			Usuario usuario, Evento evento) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.fechaEnvio = fechaEnvio;
		this.leida = leida;
		this.usuario = usuario;
		this.evento = evento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public LocalDateTime getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(LocalDateTime fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public boolean isLeida() {
		return leida;
	}

	public void setLeida(boolean leida) {
		this.leida = leida;
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
    
}