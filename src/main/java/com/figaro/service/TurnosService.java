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
	
	private static final String CATEGORIA_TURNOS = "Turnos";

	final static Logger LOGGER = Logger.getLogger(TurnosService.class);
	
	private ClientesService clientesService;
	private TurnosRepository repository;
	private MovimientosRepository movimientosRepository;
	
	public Turno saveTurno(Turno turno) {
		LOGGER.info("Guardando un nuevo turno: " + turno.toString());
		validateTurno(turno);
		int newID = getRepository().saveTurno(turno);
		turno.setId(newID);
		LOGGER.info("El turno se guardó correctamente");
		return turno ;  
	}
	
	public Turno getTurno(int turnoId) {
		LOGGER.debug("Obteniendo el turno con ID: " + turnoId);
		return repository.getTurno(turnoId);
	}
	
	public List<Turno> getTurnosCliente(int clienteId) {
		LOGGER.debug("Obteniendo lost turnos para el cliente con ID: " +  clienteId);
		return repository.getTurnosCliente(clienteId);
	}

	public List<Turno> getTurnosDelDia(Date fecha) {
		LOGGER.debug("Obteniendo turnos del dia: " + fecha );
		return searchTurno(fecha);
	}

	public List<Turno> searchTurno(Date desde) {
		return repository.searchTurno(desde);
	}
	
	public Turno setCobrado(int turnoId, Movimiento cobro) {
		Turno turno = getTurno(turnoId);
		LOGGER.info("Cobrando el Turno: " + turno.toString() + " Con el movimiento: " + cobro.toString());
		turno.setCobrado(true);
		turno.setMovimiento(generateCobro(turno,cobro));
		repository.updateTurno(turno);
		Cliente cliente = turno.getCliente();
		cliente.setUltimaVisita(turno.getDesde());
		clientesService.updateCliente(cliente);
		return turno;
	}

	
	public Turno cancelCobro(int turnoId) {
		Turno turno = getTurno(turnoId);
		LOGGER.info("Cancelando el cobro del Turno: " + turno.toString());
		turno.setCobrado(false);
		turno.setMovimiento(null);
		repository.updateTurno(turno);
		return turno;
	}
	
	public Turno updateTurno(Turno turno) {
		LOGGER.info("Actualizando el Turno: " + turno.toString());
		validateTurno(turno);
		turno.setMovimiento(updateMovimiento(turno));
		Turno updated = getTurno(turno.getId());
		updated.update(turno);
		repository.updateTurno(updated);
		LOGGER.info("El turno se actualizó correctamente");
		return updated;
	}

	private Movimiento generateCobro(Turno turno,Movimiento cobro) {
		Movimiento movimiento = new Movimiento();
		movimiento.setCategoria(CATEGORIA_TURNOS);
		movimiento.setIsGasto(false);
		movimiento.setFecha(turno.getHasta());
		BigDecimal precio = new BigDecimal(0);
		for (Trabajo t : turno.getTrabajos())
			precio = precio.add(t.getPrecio());
		movimiento.setPrecio(precio);
		List<String> descripionesTrabjo = turno.getTrabajos().stream().map(Trabajo::getDescripcion).collect(Collectors.toList());
		movimiento.setDetalle(String.join(" ", descripionesTrabjo)) ;
		movimiento.setTipoPago(cobro.getTipoPago());
		return movimiento;
	}
	
	
	private Movimiento updateMovimiento(Turno turno) {
		if (!turno.getCobrado())
			return null;
		return generateCobro(turno,turno.getMovimiento());
	}
	
	public Turno deleteTurno(int turnoId) {
		Turno turno = getTurno(turnoId);
		LOGGER.info("Eliminando turno: "+turno.toString());
		repository.deleteTurno(turno);
		LOGGER.info("El turno se eliminó correctamente");
		return turno;
	}
	
	private void validateTurno(Turno nuevoTurno) {
		LOGGER.debug("Validando el Turno: " + nuevoTurno.getDesde() +" - " +nuevoTurno.getHasta() +" "+ nuevoTurno.getPeluquero() );
		if (horarioInvalido(nuevoTurno))
			throw new HorarioInvalidoException(nuevoTurno.getDesde() +" - "+nuevoTurno.getHasta());
		List<Turno> turnosDelDia = searchTurno(nuevoTurno.getDesde());
		turnosDelDia.remove(nuevoTurno);
		for(Turno turno : turnosDelDia) 
			if(mismoPeluquero(nuevoTurno, turno) && horarioOcupado(nuevoTurno, turno)) 
			throw new TurnoOcupadoException(nuevoTurno);
	}

	private boolean horarioOcupado(Turno nuevoTurno, Turno turno) {
		return horarioInicioOcupado(nuevoTurno, turno) || horarioFinOcupado(nuevoTurno, turno) || mismoHorario(nuevoTurno, turno);
	}
	
	private boolean horarioInicioOcupado(Turno nuevoTurno, Turno turno) {
		return turno.getDesde().after(nuevoTurno.getDesde()) && turno.getDesde().before(nuevoTurno.getHasta());
	}
	
	private boolean horarioFinOcupado(Turno nuevoTurno, Turno turno) {
		return turno.getHasta().after(nuevoTurno.getDesde()) && turno.getHasta().before(nuevoTurno.getHasta());
	}

	private boolean mismoHorario(Turno nuevoTurno, Turno turno) {
		return (turno.getDesde().compareTo(nuevoTurno.getDesde()) == 0) && (turno.getHasta().compareTo(nuevoTurno.getHasta()) == 0);
	}
	
	private boolean mismoPeluquero(Turno nuevoTurno, Turno turno) {
		return turno.getPeluquero().equals(nuevoTurno.getPeluquero());
	}
	
	private boolean horarioInvalido(Turno nuevoTurno) {
		return nuevoTurno.getDesde().compareTo(nuevoTurno.getHasta()) >= 0;
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