package com.example.webthymeleaf.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int puntuacion;

    private String comentario;

    private LocalDateTime fecha;

    @OneToOne
    @JoinColumn(name = "reserva_id", nullable = false, unique = true)
    private Reserva reserva;

	public Valoracion() {
		super();
	}

	public Valoracion(Long id, int puntuacion, String comentario, LocalDateTime fecha, Reserva reserva) {
		super();
		this.id = id;
		this.puntuacion = puntuacion;
		this.comentario = comentario;
		this.fecha = fecha;
		this.reserva = reserva;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
    
    
}