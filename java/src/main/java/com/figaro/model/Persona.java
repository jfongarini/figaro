package com.figaro.model;

import java.util.Date;

public abstract class Persona {
	private int id;
	private String nombre;
	private String apellido;
	private String email;
	private String sexo;
	private Integer telefono;
	private String dirCiudad;
	private String dirCalle;
	private int dirNumeroCalle;
	private int dirPiso;
	private String dirDpto;
	private Salon salon;
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
	
	public Integer getTelefono() {
		return telefono;
	}
	public void setTelefono(Integer telefono) {
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
	public Salon getSalon() {
		return salon;
	}
	public void setSalon(Salon salon) {
		this.salon = salon;
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
	public int getDirNumeroCalle() {
		return dirNumeroCalle;
	}
	public void setDirNumeroCalle(int dirNumeroCalle) {
		this.dirNumeroCalle = dirNumeroCalle;
	}
	public int getDirPiso() {
		return dirPiso;
	}
	public void setDirPiso(int dirPiso) {
		this.dirPiso = dirPiso;
	}
	public String getDirDpto() {
		return dirDpto;
	}
	public void setDirDpto(String dirDpto) {
		this.dirDpto = dirDpto;
	}
	
}
