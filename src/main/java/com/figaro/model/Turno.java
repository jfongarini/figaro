package com.figaro.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static com.figaro.util.Constants.*;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Turno {

	private int id;
	private Cliente cliente;
	private String peluquero;
	@JsonFormat(pattern=DATE_TIME_FORMAT)
	private Date desde;
	@JsonFormat(pattern=DATE_TIME_FORMAT)
	private Date hasta;
	private Boolean cobrado;
	private Movimiento movimiento;
	private List<Trabajo> trabajos;
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getPeluquero() {
		return peluquero;
	}
	public void setPeluquero(String peluquero) {
		this.peluquero = peluquero;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean getCobrado() {
		return cobrado;
	}
	public void setCobrado(Boolean cobrado) {
		this.cobrado = cobrado;
	}
	public List<Trabajo> getTrabajos() {
		return trabajos;
	}
	public void setTrabajos(List<Trabajo> trabajos) {
		this.trabajos = trabajos;
	}
	public Date getHasta() {
		return hasta;
	}
	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	public Date getDesde() {
		return desde;
	}
	public void setDesde(Date desde) {
		this.desde = desde;
	}
	public void update(Turno turno) {
		this.cliente = turno.getCliente();
		this.peluquero = turno.getPeluquero();
		this.desde = turno.getDesde();
		this.hasta = turno.getHasta();
		this.cobrado = turno.getCobrado();
		this.movimiento = turno.getMovimiento();
		this.trabajos.removeAll(new ArrayList<Trabajo>(this.trabajos));
		for(Trabajo t : turno.getTrabajos())
			t.setId(null);
		this.trabajos.addAll(turno.getTrabajos());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Turno other = (Turno) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}
}