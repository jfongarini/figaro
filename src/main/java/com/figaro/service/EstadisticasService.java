package com.figaro.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.figaro.model.Cliente;
import com.figaro.repository.EstadisticasRepository;

public class EstadisticasService {
	
	final static Logger LOGGER = Logger.getLogger(EstadisticasService.class);
	
	private EstadisticasRepository repository;
	private ClientesService clientesService;
	
	public Map<String, Integer> buscarClienteCiudad(){
		List<Cliente> allClientes = clientesService.getAllClientes();
		Map<String, Integer> mapClientes = new HashMap<String, Integer>();
		for (Cliente cliente : allClientes) {
			String ciudad = cliente.getDirCiudad();
			Integer cantidadHabitantes = mapClientes.get(ciudad);
			if (cantidadHabitantes == null) {
				mapClientes.put(ciudad, 1);
			} else {
				cantidadHabitantes ++;
				mapClientes.put(ciudad, cantidadHabitantes);
			}
		}
		return mapClientes;
	}
	
	public Map<String, Integer> buscarClienteSexo(){
		List<Cliente> allClientes = clientesService.getAllClientes();
		Map<String, Integer> mapClientes = new HashMap<String, Integer>();
		for (Cliente cliente : allClientes) {
			String sexo = cliente.getSexo();
			Integer cantidadHabitantes = mapClientes.get(sexo);
			if (cantidadHabitantes == null) {
				mapClientes.put(sexo, 1);
			} else {
				cantidadHabitantes ++;
				mapClientes.put(sexo, cantidadHabitantes);
			}
		}
		return mapClientes;
	}

	public EstadisticasRepository getRepository() {
		return repository;
	}

	public void setRepository(EstadisticasRepository repository) {
		this.repository = repository;
	}

	public ClientesService getClientesService() {
		return clientesService;
	}

	public void setClientesService(ClientesService clientesService) {
		this.clientesService = clientesService;
	}
	
}