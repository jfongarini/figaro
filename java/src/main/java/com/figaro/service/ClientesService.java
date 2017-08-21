package com.figaro.service;

import java.util.List;

import com.figaro.model.Cliente;
import com.figaro.repository.ClientesRepository;

public class ClientesService {

	private ClientesRepository repository;
	
	public Cliente getCliente(int clienteID) {
		return repository.getCliente(clienteID);
	}

	public void saveCliente(Cliente cliente) {
		repository.saveCliente(cliente);
	}
	
	public List<Cliente> getAll() {
		return repository.getAll();
	}

	public List<Cliente> getAllClientes(){
		return repository.getAll();
	}
	
	public ClientesRepository getRepository() {
		return repository;
	}
	public void setRepository(ClientesRepository repository) {
		this.repository = repository;
	}

	
}
