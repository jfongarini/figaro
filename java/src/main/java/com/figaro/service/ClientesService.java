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

	public Integer saveCliente(Cliente cliente) {
		LOGGER.info("Guardando el cliente con ID: " + cliente.getId()+" con:"+ cliente.toString());
		return  repository.saveCliente(cliente);  
	}
	
	public Cliente updateCliente(Cliente cliente) {
		Cliente old = getCliente(cliente.getId());
		LOGGER.info("Actualizando el cliente con ID: " + old.getId()+" con:"+ cliente.toString());
		old.setNombre(cliente.getNombre());
		old.setApellido(cliente.getApellido());
		old.setEmail(cliente.getEmail());
		old.setSexo(cliente.getSexo());
		old.setTelefono(cliente.getTelefono());
		old.setDirCiudad(cliente.getDirCiudad());
		old.setDirCalle(cliente.getDirCalle());
		old.setDirNumeroCalle(cliente.getDirNumeroCalle());
		old.setDirPiso(cliente.getDirPiso());
		old.setDirDpto(cliente.getDirDpto());
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