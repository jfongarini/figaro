package com.figaro.repository;


import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.query.Query;

import com.figaro.model.Movimiento;
import com.figaro.service.MovimientosService;

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
	
	@SuppressWarnings({ "unchecked" })
	public List<Movimiento> buscar(Date search1, Date search2, String searchC) {
		String querySql = QUERY_GET_MOVIMIENTOS;
		if(!"".equals(searchC))
			querySql += QUERY_CATEGORIA;
		Query<Movimiento> query = getCurrentSession().createQuery(querySql);
	    query.setParameter(1, search1);
	    query.setParameter(2, search2);
	    if(!"".equals(searchC))
	    	query.setParameter(3, searchC);
	    return query.getResultList();
	}	

}	