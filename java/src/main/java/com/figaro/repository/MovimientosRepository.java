package com.figaro.repository;

import java.util.List;

import com.figaro.model.Movimiento;


public class MovimientosRepository extends AbstractRepository{

	public int saveMovimiento (Movimiento movimiento) {
		return (int) getCurrentSession().save(movimiento); 
	}

	public void updateMovimiento(Movimiento movimiento) {
		getCurrentSession().update(movimiento);
	}
	
	public Movimiento getMovimiento(Long idMovimiento){
		return (Movimiento) getCurrentSession().get(Movimiento.class, idMovimiento);
	}

	@SuppressWarnings("unchecked")
	public List<Movimiento> getAll() {
		return getCurrentSession().createQuery("from Movimiento").list();
	}

}	