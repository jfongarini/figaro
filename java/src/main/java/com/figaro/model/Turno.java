package com.figaro.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Turno {

	private int id;
	private Cliente cliente;
	private String peluquero;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date desde;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date hasta;
	private Boolean cobrado;
	private List<Trabajo> trabajos;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getPeluquero() {
		return peluquero;
	}
	public void setPeluquero(String peluquero) {
		this.peluquero = peluquero;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean getCobrado() {
		return cobrado;
	}
	public void setCobrado(Boolean cobrado) {
		this.cobrado = cobrado;
	}
	public List<Trabajo> getTrabajos() {
		return trabajos;
	}
	public void setTrabajos(List<Trabajo> trabajos) {
		this.trabajos = trabajos;
	}
	public Date getHasta() {
		return hasta;
	}
	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	public Date getDesde() {
		return desde;
	}
	public void setDesde(Date desde) {
		this.desde = desde;
	}
	
}