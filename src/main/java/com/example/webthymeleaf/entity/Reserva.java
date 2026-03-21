package com.example.webthymeleaf.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    private int numPersonas;

    private String estado;

    private String metodoPago;

    private int bolitosGenerados;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "pista_id", nullable = false)
    private Pista pista;

    @ManyToOne
    @JoinColumn(name = "franja_id", nullable = false)
    private FranjaHoraria franjaHoraria;

    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private Valoracion valoracion;

	public Reserva() {
		super();
	}

	public Reserva(Long id, LocalDateTime fecha, int numPersonas, String estado, String metodoPago,
			int bolitosGenerados, Usuario usuario, Pista pista, FranjaHoraria franjaHoraria, Valoracion valoracion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.numPersonas = numPersonas;
		this.estado = estado;
		this.metodoPago = metodoPago;
		this.bolitosGenerados = bolitosGenerados;
		this.usuario = usuario;
		this.pista = pista;
		this.franjaHoraria = franjaHoraria;
		this.valoracion = valoracion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public int getBolitosGenerados() {
		return bolitosGenerados;
	}

	public void setBolitosGenerados(int bolitosGenerados) {
		this.bolitosGenerados = bolitosGenerados;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pista getPista() {
		return pista;
	}

	public void setPista(Pista pista) {
		this.pista = pista;
	}

	public FranjaHoraria getFranjaHoraria() {
		return franjaHoraria;
	}

	public void setFranjaHoraria(FranjaHoraria franjaHoraria) {
		this.franjaHoraria = franjaHoraria;
	}

	public Valoracion getValoracion() {
		return valoracion;
	}

	public void setValoracion(Valoracion valoracion) {
		this.valoracion = valoracion;
	}
    
}