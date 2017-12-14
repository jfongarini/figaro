package com.figaro.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Peluquero extends Persona {
	
	private Set<Trabajo> trabajos;
	private Integer comision;
	
	public Peluquero() {
		this.trabajos = new HashSet<Trabajo>();
	}
	

	public void update(Peluquero peluquero) {
		this.setNombre(peluquero.getNombre());
		this.setApellido(peluquero.getApellido());
		this.setEmail(peluquero.getEmail());
		this.setSexo(peluquero.getSexo());
		this.setTelefono(peluquero.getTelefono());
		this.setDirCiudad(peluquero.getDirCiudad());
		this.setDirCalle(peluquero.getDirCalle());
		this.setDirNumeroCalle(peluquero.getDirNumeroCalle());
		this.setDirPiso(peluquero.getDirPiso());
		this.setDirDpto(peluquero.getDirDpto());
		this.setComision(peluquero.getComision());
	
		this.trabajos.removeAll(new ArrayList<Trabajo>(this.trabajos));
		this.trabajos.addAll(peluquero.getTrabajos());
	}
	
	public Set<Trabajo> getTrabajos() {
		return trabajos;
	}

	public void setTrabajos(Set<Trabajo> trabajos) {
		this.trabajos = trabajos;
	}

	public Integer getComision() {
		return comision;
	}

	public void setComision(Integer comision) {
		this.comision = comision;
	}

	
}
