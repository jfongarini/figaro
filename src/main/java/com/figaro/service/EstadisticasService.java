package com.figaro.service;



import java.util.List;

import org.apache.log4j.Logger;

import com.figaro.model.Cliente;
import com.figaro.model.Ciudad;
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
	
	public List<Ciudad> getCiudades(){
		LOGGER.debug("Obteniendo todos las ciudades");
		return repository.getCiudades();
	}
	
	public List<Cliente> buscarClienteCiudad(String search){
		LOGGER.debug("Obteniendo clientes por ciudad");
		return repository.buscarClienteCiudad(search);
	}	
	

	public EstadisticasRepository getRepository() {
		return repository;
	}

	public void setRepository(EstadisticasRepository repository) {
		this.repository = repository;
	}
	
}