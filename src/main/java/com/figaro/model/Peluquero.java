package com.figaro.model;

import java.util.List;

public class Peluquero extends Persona {
	
	private List<Trabajo> trabajos;
	private int comision;

	public List<Trabajo> getTrabajos() {
		return trabajos;
	}

	public void setTrabajos(List<Trabajo> trabajos) {
		this.trabajos = trabajos;
	}

	public int getComision() {
		return comision;
	}

	public void setComision(int comision) {
		this.comision = comision;
	}

	
}
