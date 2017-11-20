package com.figaro.service;



import java.util.List;

import org.apache.log4j.Logger;

import com.figaro.model.Cliente;
import com.figaro.repository.EstadisticasRepository;

public class EstadisticasService {
	
	final static Logger LOGGER = Logger.getLogger(EstadisticasService.class);
	
	private EstadisticasRepository repository;
	
	public List<Cliente> buscarClienteSexo(String search) {
		return repository.buscarClienteSexo(search);
	}
	
	public List<Cliente> getAllClientes(){
		LOGGER.debug("Obteniendo todos los clientes");
		return repository.getAll();
	}

	public EstadisticasRepository getRepository() {
		return repository;
	}

	public void setRepository(EstadisticasRepository repository) {
		this.repository = repository;
	}
	
}