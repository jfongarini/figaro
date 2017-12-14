package com.figaro.model;

import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Trabajo {
	
	private Integer id;
	private String descripcion;
	private BigDecimal precio;
	@JsonIgnore
	private Set<Peluquero> peluqueros;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Set<Peluquero> getPeluqueros() {
		return peluqueros;
	}
	public void setPeluqueros(Set<Peluquero> peluqueros) {
		this.peluqueros = peluqueros;
	}
	
}
