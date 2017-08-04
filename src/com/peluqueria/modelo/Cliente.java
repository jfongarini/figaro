package com.peluqueria.modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

	private long id;
	private List <Turno> historia;

	public Cliente() {
		this.historia = new ArrayList<Turno>();		
	}
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List <Turno> getHistoria() {
		return historia;
	}

	public void setHistoria(List <Turno> historia) {
		this.historia = historia;
	}
}
