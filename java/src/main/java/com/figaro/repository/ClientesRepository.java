package com.figaro.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.figaro.model.Cliente;

@Component
public class ClientesRepository extends AbstractRepository{

	public void saveCliente (Cliente cliente) {
		getCurrentSession().save(cliente);
	}
	
	public Cliente getCliente(int id){
		return (Cliente) getCurrentSession().get(Cliente.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> getAll() {
		return getCurrentSession().createQuery("from Cliente ").list();		
	}
	
}
