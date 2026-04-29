package com.example.webthymeleaf.model;

import java.time.LocalDateTime;

public class ReservaRequestDTO {
	private Long usuarioId;
	private Long pistaId;
	private Long franjaId;
	private LocalDateTime fecha;
	private int numPersonas;
	private String estado;
	private String metodoPago;
	private int bolitosGenerados;

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Long getPistaId() {
		return pistaId;
	}

	public void setPistaId(Long pistaId) {
		this.pistaId = pistaId;
	}

	public Long getFranjaId() {
		return franjaId;
	}

	public void setFranjaId(Long franjaId) {
		this.franjaId = franjaId;
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
}