package com.figaro.model;

import java.math.BigDecimal;
import java.util.Date;


public class Movimiento {

	private int id;
	private Salon salon;
	private Categoria categoria;
	private BigDecimal precio;
	private Date fecha;
	private String detalle;
	private Boolean isGasto;	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Salon getSalon() {
		return salon;
	}
	public void setSalon(Salon salon) {
		this.salon = salon;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Boolean getIsGasto() {
		return isGasto;
	}	
	public void setIsGasto(Boolean isGasto) {
		this.isGasto = isGasto;
	}	
	public void update(Movimiento movimiento) {		
		this.setCategoria(getCategoria());
		this.setDetalle(getDetalle());
		this.setFecha(getFecha());
		this.setIsGasto(getIsGasto());
		this.setPrecio(getPrecio());
		this.setSalon(getSalon());		
	}
	
}