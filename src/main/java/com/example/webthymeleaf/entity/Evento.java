package com.example.webthymeleaf.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    private LocalDateTime fecha;

    private String tipo;

    private String premio;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParticipacionEvento> participaciones = new ArrayList<>();

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    private List<Notificacion> notificaciones = new ArrayList<>();

	public Evento() {
		super();
	}

	public Evento(Long id, String nombre, String descripcion, LocalDateTime fecha, String tipo, String premio,
			List<ParticipacionEvento> participaciones, List<Notificacion> notificaciones) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.tipo = tipo;
		this.premio = premio;
		this.participaciones = participaciones;
		this.notificaciones = notificaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPremio() {
		return premio;
	}

	public void setPremio(String premio) {
		this.premio = premio;
	}

	public List<ParticipacionEvento> getParticipaciones() {
		return participaciones;
	}

	public void setParticipaciones(List<ParticipacionEvento> participaciones) {
		this.participaciones = participaciones;
	}

	public List<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}
    
}