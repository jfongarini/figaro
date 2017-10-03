package com.figaro.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import com.figaro.exception.TurnoOcupadoException;
import com.figaro.model.Turno;
import com.figaro.repository.TurnosRepository;
import static com.figaro.util.Utils.*;

public class TurnosService {
	
	final static Logger LOGGER = Logger.getLogger(TurnosService.class);
	
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

	private void validateTurno(Turno turno) {
		if ( turno.getDesde().compareTo(turno.getHasta()) >= 0 )
			throw new RuntimeException();
		List<Turno> turnosDelDia = searchTurno(turno.getDesde());
		List<Turno> turnosFranjaHoraria = new ArrayList<Turno>();
		for(Turno t : turnosDelDia) 
			if((t.getDesde().after(turno.getDesde()) && t.getDesde().before(turno.getHasta())) || 
			   (t.getHasta().after(turno.getDesde()) && t.getHasta().before(turno.getHasta()))) 
				turnosFranjaHoraria.add(t);
		Optional<Turno> turnoDePeluquero = turnosFranjaHoraria.stream().filter(t -> t.getPeluquero().equals(turno.getPeluquero())).findFirst();
		if (turnosFranjaHoraria.size() > 0 && turnoDePeluquero.isPresent()) 
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

	

	
	
	
	

}