package com.figaro.service;



import org.apache.log4j.Logger;

import com.figaro.repository.EstadisticasRepository;

public class EstadisticasService {
	
	final static Logger LOGGER = Logger.getLogger(EstadisticasService.class);
	
	private EstadisticasRepository repository;

	public EstadisticasRepository getRepository() {
		return repository;
	}

	public void setRepository(EstadisticasRepository repository) {
		this.repository = repository;
	}
	
}