package com.figaro.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Caja {

	private Long id;
	private List <Movimiento> movimiento;
	private Salon salon;
	private BigDecimal saldo;
	
	
	public Caja() {
		this.movimiento = new ArrayList<Movimiento>();		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Movimiento> getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(List<Movimiento> movimiento) {
		this.movimiento = movimiento;
	}

	public Salon getSalon() {
		return salon;
	}

	public void setSalon(Salon salon) {
		this.salon = salon;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	
}
