package com.figaro.service;


import java.util.List;

import org.apache.log4j.Logger;

import com.figaro.model.Peluquero;
import com.figaro.repository.PeluquerosRepository;

public class PeluquerosService {
	
	final static Logger LOGGER = Logger.getLogger(PeluquerosService.class);

	private PeluquerosRepository repository;
	
	
	public Peluquero getPeluquero(int peluqueroId) {
		LOGGER.debug("Obteniendo el peluquero con ID: " + peluqueroId);
		return repository.getPeluquero(peluqueroId);
	}
	
	public Integer savePeluquero(Peluquero peluquero) {
		LOGGER.info("Guardando el nuevo peluquero: "+ peluquero.getNombre());
		return repository.savePeluquero(peluquero);
	}

	public void deletePeluquero(Integer idPeluquero) {
		repository.deletePeluquero(idPeluquero);
	}
	
	public List<Peluquero> getPeluqueros() {
		LOGGER.debug("Obteniendo todos los peluqueros");
		return repository.getPeluqueros();
	}
	
	
	public void setRepository(PeluquerosRepository repository) {
		this.repository = repository;
	}

	

}
