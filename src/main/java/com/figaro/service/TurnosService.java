package com.figaro.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.figaro.exception.HorarioInvalidoException;
import com.figaro.exception.TurnoOcupadoException;
import com.figaro.model.Cliente;
import com.figaro.model.Movimiento;
import com.figaro.model.Trabajo;
import com.figaro.model.Turno;
import com.figaro.repository.MovimientosRepository;
import com.figaro.repository.TurnosRepository;

public class TurnosService {
	
	final static Logger LOGGER = Logger.getLogger(TurnosService.class);
	
	private ClientesService clientesService;
	private TurnosRepository repository;
	private MovimientosRepository movimientosRepository;
	
	public Turno saveTurno(Turno turno) {
		LOGGER.info("Guardando un nuevo turno para: " + turno.getDesde() +" - "+turno.getHasta() +" " + turno.getPeluquero());
		validateTurno(turno);
		int newID = getRepository().saveTurno(turno);
		turno.setId(newID);
		LOGGER.info("Se guardo el nuevo turno con ID: " + turno.getId());
		return turno ;  
	}
	
	public Turno getTurno(int turnoId) {
		LOGGER.debug("Obteniendo el turno con ID: " +  turnoId);
		return repository.getTurno(turnoId);
	}
	
	public List<Turno> getTurnosCliente(int clienteId) {
		LOGGER.debug("Obteniendo lost turnos para el cliente con ID: " +  clienteId);
		return repository.getTurnosCliente(clienteId);
	}

	
	public Turno setCobrado(int turnoId) {
		LOGGER.info("Modificando cobro del Turno con ID: " + turnoId);
		Turno turno = getTurno(turnoId);
		turno.setCobrado(!turno.getCobrado());
		turno.setMovimiento(generateMovimiento(turno));
		repository.updateTurno(turno);
		Cliente cliente = turno.getCliente();
		cliente.setUltimaVisita(turno.getDesde());
		clientesService.updateCliente(cliente);
		return turno;
	}

	public Turno updateTurno(Turno turno) {
		LOGGER.info("Actualizando el Turno con ID: " + turno.getId());
		validateTurno(turno);
		turno.setMovimiento(generateMovimiento(turno));
		Turno old = getTurno(turno.getId());
		old.update(turno);
		repository.updateTurno(old);
		LOGGER.info("El turno se actualizó correctamente");
		return turno;
	}

	private Movimiento generateMovimiento(Turno turno) {
		if (!turno.getCobrado())
			return null;
		Movimiento movimiento = new Movimiento();
		movimiento.setCategoria("Turnos");
		movimiento.setIsGasto(false);
		movimiento.setFecha(turno.getHasta());
		BigDecimal precio = new BigDecimal(0);
		for (Trabajo t : turno.getTrabajos())
			precio = precio.add(t.getPrecio());
		movimiento.setPrecio(precio);
		List<String> descripionesTrabjo = turno.getTrabajos().stream().map(Trabajo::getDescripcion).collect(Collectors.toList());
		movimiento.setDetalle(String.join(" ", descripionesTrabjo)) ;
		return movimiento;
	}
	
	public Turno deleteTurno(int turnoId) {
		Turno turno = getTurno(turnoId);
		LOGGER.info("Eliminando turno para: " + turno.getDesde() +" - "+turno.getHasta() +" " + turno.getPeluquero());
		repository.deleteTurno(turno);
		LOGGER.info("El turno se eliminó correctamente");
		return turno;
	}
	

	private void validateTurno(Turno turno) {
		LOGGER.debug("Validando el Turno: " + turno.getDesde() +" - "+turno.getHasta() +" " + turno.getPeluquero() );
		if ( turno.getDesde().compareTo(turno.getHasta()) >= 0 )
			throw new HorarioInvalidoException();
		List<Turno> turnosDelDia = searchTurno(turno.getDesde());
		turnosDelDia.remove(turno);
		for(Turno t : turnosDelDia) 
			if(((t.getDesde().after(turno.getDesde()) && t.getDesde().before(turno.getHasta())) || 
			    (t.getHasta().after(turno.getDesde()) && t.getHasta().before(turno.getHasta())) ||
			    (t.getDesde().compareTo(turno.getDesde()) == 0) && (t.getHasta().compareTo(turno.getHasta()) == 0)) &&
				t.getPeluquero().equals(turno.getPeluquero())) 
			throw new TurnoOcupadoException();
	}
	
	public List<Turno> getTurnosDelDia(Date fecha) {
		LOGGER.info("Obteniendo turnos del dia: " + fecha );
		return searchTurno(fecha);
	}

	public List<Turno> searchTurno(Date desde) {
		return repository.searchTurno(desde);
	}

	public TurnosRepository getRepository() {
		return repository;
	}

	public void setRepository(TurnosRepository repository) {
		this.repository = repository;
	}


	public MovimientosRepository getMovimientosRepository() {
		return movimientosRepository;
	}

	public void setMovimientosRepository(MovimientosRepository movimientosRepository) {
		this.movimientosRepository = movimientosRepository;
	}

	public ClientesService getClientesService() {
		return clientesService;
	}

	public void setClientesService(ClientesService clientesService) {
		this.clientesService = clientesService;
	}

}