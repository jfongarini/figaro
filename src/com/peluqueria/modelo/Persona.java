package com.peluqueria.modelo;

public abstract class Persona {

	private String nombre;
	private String apellido;
	private String email;
	private Boolean masculino;
	private Integer telefono;
	private Integer telefono2;
	private String dirCiudad;
	private String dirCalle;
	private String dirNumeroCalle;
	private String dirPiso;
	private String dirDpto;
	private Salon salon;
	
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
	public Boolean getMasculino() {
		return masculino;
	}
	public void setMasculino(Boolean masculino) {
		this.masculino = masculino;
	}
	public Integer getTelefono() {
		return telefono;
	}
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	public Integer getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(Integer telefono2) {
		this.telefono2 = telefono2;
	}
	public String getDirCalle() {
		return dirCalle;
	}
	public void setDirCalle(String dirCalle) {
		this.dirCalle = dirCalle;
	}
	public String getDirNumeroCalle() {
		return dirNumeroCalle;
	}
	public void setDirNumeroCalle(String dirNumeroCalle) {
		this.dirNumeroCalle = dirNumeroCalle;
	}
	public String getDirPiso() {
		return dirPiso;
	}
	public void setDirPiso(String dirPiso) {
		this.dirPiso = dirPiso;
	}
	public String getDirDpto() {
		return dirDpto;
	}
	public void setDirDpto(String dirDpto) {
		this.dirDpto = dirDpto;
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
	
	
}
