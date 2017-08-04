package com.peluqueria.modelo;

public class Peluquero extends Persona {
	private long id;
	private Salon salon;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Salon getSalon() {
		return salon;
	}

	public void setSalon(Salon salon) {
		this.salon = salon;
	}

}
