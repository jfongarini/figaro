package com.figaro.model;

import java.math.BigDecimal;
import java.util.Date;


public class Movimiento {

	
	private Integer id;
	private String categoria;
	private BigDecimal precio;
	private Date fecha;
	private String detalle;
	private Boolean isGasto;	
	
	public void update(Movimiento movimiento) {		
		this.setCategoria(movimiento.getCategoria());
		this.setDetalle(movimiento.getDetalle());
		this.setFecha(movimiento.getFecha());
		this.setIsGasto(movimiento.getIsGasto());
		this.setPrecio(movimiento.getPrecio());
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", categoria=" + categoria + ", precio=" + precio + ", fecha=" + fecha + ", detalle=" + detalle + ", isGasto=" + isGasto + "]";
	}
	
}