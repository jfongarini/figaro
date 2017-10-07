package com.figaro.service;

import static com.figaro.util.Utils.stringToDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.figaro.exception.HorarioInvalidoException;
import com.figaro.exception.TurnoOcupadoException;
import com.figaro.model.Cliente;
import com.figaro.model.Turno;
import com.figaro.repository.TurnosRepository;

public class TurnosService {
	
	final static Logger LOGGER = Logger.getLogger(TurnosService.class);
	
	@Autowired
	@Qualifier("ClientesServiceTransactional")
	private ClientesService ClienteService;
	
	private TurnosRepository repository;
	
	public Turno saveTurno(Turno turno) {
		validateTurno(turno);
		int newID = getRepository().saveTurno(turno);
		turno.setId(newID);
		return turno ;  
	}
	
	public Turno getTurno(int turnoId) {
		return repository.getTurno(turnoId);
	}
	
	public Turno setCobrado(int turnoId) {
		Turno turno = getTurno(turnoId);
		turno.setCobrado(!turno.getCobrado());
		Cliente cliente = turno.getCliente();
		cliente.setUltimaVisita(turno.getDesde());
		ClienteService.updateCliente(cliente);
		repository.updateTurno(turno);
		return turno;
	}

	
	public Turno updateTurno(Turno turno) {
		validateTurno(turno);
		Turno old = getTurno(turno.getId());
		LOGGER.info("Actualizando el Turno con ID: " + old.getId()+" con:"+ turno.toString());
		old.update(turno);
		repository.updateTurno(old);
		return turno;
	}


	private void validateTurno(Turno turno) {
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
	
	public List<Turno> getTurnosDelDia(String fecha) {
		return searchTurno(stringToDate(fecha));
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

	public ClientesService getClienteService() {
		return ClienteService;
	}
	public void setClienteService(ClientesService clienteService) {
		ClienteService = clienteService;
	}

	
	
	

	
	
	
	

}