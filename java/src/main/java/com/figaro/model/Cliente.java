package com.figaro.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cliente extends Persona {
	
	private Date ultimaVisita;
	private List <Turno> historia;

	public Cliente() {
		this.historia = new ArrayList<Turno>();		
	}

	public List <Turno> getHistoria() {
		return historia;
	}

	public void setHistoria(List <Turno> historia) {
		this.historia = historia;
	}

	public Date getUltimaVisita() {
		return ultimaVisita;
	}

	public void setUltimaVisita(Date ultimaVisita) {
		this.ultimaVisita = ultimaVisita;
	}
}
