package com.example.webthymeleaf.model;

import java.time.LocalDateTime;

public class CanjeRecompensaRequestDTO {
	private Long usuarioId;
	private Long recompensaId;
	private int cantidadCanjeada;
	private int bolitosGastados;
	private LocalDateTime fechaCanje;

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Long getRecompensaId() {
		return recompensaId;
	}

	public void setRecompensaId(Long recompensaId) {
		this.recompensaId = recompensaId;
	}

	public int getCantidadCanjeada() {
		return cantidadCanjeada;
	}

	public void setCantidadCanjeada(int cantidadCanjeada) {
		this.cantidadCanjeada = cantidadCanjeada;
	}

	public int getBolitosGastados() {
		return bolitosGastados;
	}

	public void setBolitosGastados(int bolitosGastados) {
		this.bolitosGastados = bolitosGastados;
	}

	public LocalDateTime getFechaCanje() {
		return fechaCanje;
	}

	public void setFechaCanje(LocalDateTime fechaCanje) {
		this.fechaCanje = fechaCanje;
	}
}