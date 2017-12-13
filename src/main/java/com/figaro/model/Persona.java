package com.figaro.model;

import java.util.Date;

public abstract class Persona {
	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private String sexo;
	private String telefono;
	private String dirCiudad;
	private String dirCalle;
	private Integer dirNumeroCalle;
	private Integer dirPiso;
	private String dirDpto;
	private Date fechaIngreso;
	
	
	

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDirCalle() {
		return dirCalle;
	}
	public void setDirCalle(String dirCalle) {
		this.dirCalle = dirCalle;
	}
	public String getDirCiudad() {
		return dirCiudad;
	}
	public void setDirCiudad(String dirCiudad) {
		this.dirCiudad = dirCiudad;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Integer getDirNumeroCalle() {
		return dirNumeroCalle;
	}
	public void setDirNumeroCalle(Integer dirNumeroCalle) {
		this.dirNumeroCalle = dirNumeroCalle;
	}
	public Integer getDirPiso() {
		return dirPiso;
	}
	public void setDirPiso(Integer dirPiso) {
		this.dirPiso = dirPiso;
	}
	public String getDirDpto() {
		return dirDpto;
	}
	public void setDirDpto(String dirDpto) {
		this.dirDpto = dirDpto;
	}
	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", sexo="+ sexo + ", telefono=" + telefono + ", dirCiudad=" + dirCiudad + ", dirCalle=" + dirCalle + ", dirNumeroCalle=" + dirNumeroCalle + ", dirPiso=" + dirPiso + ", dirDpto=" + dirDpto + ", fechaIngreso=" + fechaIngreso + "]";
	}
}
