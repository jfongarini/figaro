package com.figaro.repository;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.query.Query;

import com.figaro.model.Movimiento;
import com.figaro.service.MovimientosService;



public class MovimientosRepository extends AbstractRepository{
	
	final static Logger LOGGER = Logger.getLogger(MovimientosService.class);

	public int saveMovimiento (Movimiento movimiento) {
		return (int) getCurrentSession().save(movimiento); 
	}

	public void updateMovimiento(Movimiento movimiento) {
		getCurrentSession().update(movimiento);
	}
	
	public Movimiento getMovimiento(int id){
		return (Movimiento) getCurrentSession().get(Movimiento.class, id);
	}
	
	public void deleteMovimiento(Movimiento movimiento) {
		getCurrentSession().delete(movimiento);
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
	
	@SuppressWarnings({ "unchecked" })
	public List<Movimiento> buscarE(Date search1, Date search2) {
				
		Query<Movimiento> query = getCurrentSession().createQuery( "FROM Movimiento m WHERE m.fecha BETWEEN ?1 AND ?2");
	    query.setParameter(1, search1);
	    query.setParameter(2, search2);
	    return query.getResultList();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Movimiento> buscarCategoria(String search) {
		Query<Movimiento> query = getCurrentSession().createQuery("FROM Movimiento m WHERE m.categoria = ?1");
	    query.setParameter(1, search);
	    return query.getResultList();
	}

}	