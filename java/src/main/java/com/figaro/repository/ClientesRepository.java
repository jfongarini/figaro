package com.figaro.repository;

import java.util.List;

import org.hibernate.query.Query;

import com.figaro.model.Cliente;

public class ClientesRepository extends AbstractRepository{

	public Integer saveCliente (Cliente cliente) {
		return (Integer) getCurrentSession().save(cliente); 
	}

	public void updateCliente(Cliente cliente) {
		getCurrentSession().update(cliente);
	}
	
	public Cliente getCliente(int id){
		return (Cliente) getCurrentSession().get(Cliente.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> getAll() {
		return getCurrentSession().createQuery("from Cliente").list();		
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> buscar(String search) {
		Query<Cliente> query = getCurrentSession().createQuery("FROM Cliente c WHERE c.nombre LIKE CONCAT('%',?1,'%') OR c.apellido LIKE CONCAT('%',?1,'%')");
	    query.setParameter(1, search);
	    return query.getResultList();
	}
	
}
