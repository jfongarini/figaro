package com.figaro.repository;

import java.util.List;

import org.hibernate.query.Query;

import com.figaro.model.Movimiento;


public class MovimientosRepository extends AbstractRepository{

	public int saveMovimiento (Movimiento movimiento) {
		return (int) getCurrentSession().save(movimiento); 
	}

	public void updateMovimiento(Movimiento movimiento) {
		getCurrentSession().update(movimiento);
	}
	
	public Movimiento getMovimiento(int id){
		return (Movimiento) getCurrentSession().get(Movimiento.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Movimiento> getAll() {
		return getCurrentSession().createQuery("from Movimiento").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Movimiento> buscar(String search) {
		Query<Movimiento> query = getCurrentSession().createQuery("FROM Movimiento m WHERE m.fecha LIKE CONCAT('%',?1,'%')");
	    query.setParameter(1, search);
	    return query.getResultList();
	}

}	