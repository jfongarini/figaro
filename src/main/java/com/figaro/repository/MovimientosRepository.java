package com.figaro.repository;


import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.query.Query;

import com.figaro.model.Item;
import com.figaro.model.Movimiento;
import com.figaro.model.Venta;
import com.figaro.service.MovimientosService;

@SuppressWarnings({ "unchecked" })
public class MovimientosRepository extends AbstractRepository{
	
	final static Logger LOGGER = Logger.getLogger(MovimientosService.class);

	private static String QUERY_GET_MOVIMIENTOS = "FROM Movimiento m WHERE (m.fecha BETWEEN ?1 AND ?2)";
	private static String QUERY_CATEGORIA = " AND (m.categoria = ?3)";
	
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
	

	public List<Movimiento> searchBetween(Date from, Date to) {
		String querySql = QUERY_GET_MOVIMIENTOS;
		Query<Movimiento> query = getCurrentSession().createQuery(querySql);
	    query.setParameter(1, from);
	    query.setParameter(2, to);
	    return query.getResultList();
	}

	public List<Movimiento> searchBetweenWithCategory(Date from, Date to, String category) {
		String querySql = QUERY_GET_MOVIMIENTOS + QUERY_CATEGORIA;
		Query<Movimiento> query = getCurrentSession().createQuery(querySql);
	    query.setParameter(1, from);
	    query.setParameter(2, to);
	    query.setParameter(3, category);
	    return query.getResultList();
	}	
	
	public Item getItemId(Integer id) {		
		
		String querySql = "from Item t WHERE (t.id = ?1)";	
		Query<Item> query = getCurrentSession().createQuery(querySql);
	    query.setParameter(1, id);	  
	    return query.getSingleResult();
		
	}
	
	public Venta getVentaId(Integer id) {		
		
		String querySql = "from Venta t WHERE (t.id = ?1)";	
		Query<Venta> query = getCurrentSession().createQuery(querySql);
	    query.setParameter(1, id);	  
	    return query.getSingleResult();
		
	}

}	