package com.figaro.repository;

import java.util.List;

import org.hibernate.query.Query;

import com.figaro.model.Cliente;

@SuppressWarnings({ "unchecked" })
public class EstadisticasRepository extends AbstractRepository{
	
	@SuppressWarnings("unchecked")
	public List<Cliente> buscarClienteSexo(String search) {
	    Query<Cliente> query = getCurrentSession().createQuery("FROM Cliente c WHERE c.sexo = ?1");
		query.setParameter(1, search);
	    return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> getAll() {
		return getCurrentSession().createQuery("from Cliente").list();		
	}
	
}
