package com.figaro.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.figaro.model.Cliente;
import com.figaro.repository.ClientesRepository;

public class ClientesService {
	
	final static Logger LOGGER = Logger.getLogger(ClientesService.class);
	
	private ClientesRepository repository;
	
	public Cliente getCliente(int clienteID) {
		LOGGER.debug("Obteniendo el cliente con ID: " + clienteID);
		return repository.getCliente(clienteID);
	}

	public Cliente saveCliente(Cliente cliente) {
		LOGGER.info("Guardando el cliente con ID: " + cliente.getId()+" con:"+ cliente.toString());
		int newID = repository.saveCliente(cliente);
		cliente.setId(newID);
		return cliente ;  
	}
	
	public Cliente updateCliente(Cliente cliente) {
		Cliente old = getCliente(cliente.getId());
		LOGGER.info("Actualizando el cliente con ID: " + old.getId()+" con:"+ cliente.toString());
		old.update(cliente);
		repository.updateCliente(old);
		return cliente;
	}
	
	public List<Cliente> getAllClientes(){
		LOGGER.debug("Obteniendo todos los clientes");
		return repository.getAll();
	}
	
	public ClientesRepository getRepository() {
		return repository;
	}
	
	public void setRepository(ClientesRepository repository) {
		this.repository = repository;
	}

	public List<Cliente> buscar(String search) {
		return repository.buscar(search);
	}

}