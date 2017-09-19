package com.figaro.model;

public abstract class Producto {
	private int id;
	private String nombre;
	private int cantidad;
	private int cantidadMin;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int unaCantidad) {
		this.cantidad = unaCantidad;
	}
	public int getCantidadMin() {
		return cantidadMin;
	}
	public void setCantidadMin(int cantMin) {
		this.cantidadMin = cantMin;
	}	

	public Producto actualizar(Producto unProducto) {
		setNombre(unProducto.getNombre());
		setCantidad(unProducto.getCantidad());
		setCantidadMin(unProducto.getCantidadMin());
		return this;
	}
}
