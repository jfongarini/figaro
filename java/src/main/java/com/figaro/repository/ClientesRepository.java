package com.figaro.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Criteria;

import org.hibernate.criterion.Restrictions;
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
		
		String hql = "from Cliente where nombre like :search";
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("search", "%"+search + "%");
		return query.list();
		
				
	}
	
}
