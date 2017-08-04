package com.peluqueria.modelo;

import java.math.BigDecimal;
import java.util.Date;

public class Turno {

	private Long id;
	private Cliente cliente;
	private Peluquero peluquero;
	private Date fecha;
	private String trabajo;
	private BigDecimal precio;
	private Salon salon;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Peluquero getPeluquero() {
		return peluquero;
	}
	public void setPeluquero(Peluquero peluquero) {
		this.peluquero = peluquero;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getTrabajo() {
		return trabajo;
	}
	public void setTrabajo(String trabajo) {
		this.trabajo = trabajo;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public Salon getSalon() {
		return salon;
	}
	public void setSalon(Salon salon) {
		this.salon = salon;
	}
	
}