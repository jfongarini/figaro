package com.figaro.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.figaro.model.Movimiento;
import com.figaro.repository.MovimientosRepository;

public class MovimientosService {

	final static Logger LOGGER = Logger.getLogger(MovimientosService.class);
	
	private MovimientosRepository repository;

	public Movimiento saveMovimiento (Movimiento movimiento) {
		Long id = (long) repository.saveMovimiento(movimiento);
		movimiento.setId(id);	
		return movimiento;
	}	

	public Movimiento getMovimiento(Long idMovimiento) {
		return repository.getMovimiento(idMovimiento);
	}
	
	public Movimiento updateMovimiento(Movimiento movimiento) {
		Movimiento old = getMovimiento(movimiento.getId());		
		old.update(movimiento);
		repository.updateMovimiento(old);
		return movimiento;
	}
	
	public MovimientosRepository getRepository() {
		return repository;
	}
	public void setRepository(MovimientosRepository repository) {
		this.repository = repository;
	}

	public List<Movimiento> getAllMovimiento() {
		LOGGER.debug("Obteniendo todos los clientes");
		return repository.getAll();
	}
	
}