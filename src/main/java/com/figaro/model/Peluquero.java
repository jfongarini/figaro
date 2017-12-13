package com.figaro.model;

import java.util.ArrayList;
import java.util.List;

public class Peluquero extends Persona {
	
	private List<Trabajo> trabajos;
	private Integer comision;
	
	public Peluquero() {
		this.trabajos = new ArrayList<Trabajo>();
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
		for(Trabajo t : peluquero.getTrabajos())
			t.setId(null);
		this.trabajos.addAll(peluquero.getTrabajos());
	}
	
	public List<Trabajo> getTrabajos() {
		return trabajos;
	}

	public void setTrabajos(List<Trabajo> trabajos) {
		this.trabajos = trabajos;
	}

	public Integer getComision() {
		return comision;
	}

	public void setComision(Integer comision) {
		this.comision = comision;
	}

	
}
