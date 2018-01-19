package com.figaro.model;

import java.math.BigDecimal;
import java.util.Date;

public class Venta {

	private int id;
	private BigDecimal precio;
	private Date fecha;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
		
	public Venta update(Venta venta) {
		setPrecio(venta.getPrecio());
		setFecha(venta.fecha);
		return this;
	}

	@Override
	public String toString() {
		return "Venta [id=" + id + ", precio=" + precio + ", fecha=" + fecha + "]";
	}
	
}
