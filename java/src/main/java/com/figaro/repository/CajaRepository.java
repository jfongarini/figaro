package com.figaro.repository;

import com.figaro.model.Caja;


public class CajaRepository extends AbstractRepository{

	public int saveCaja (Caja caja) {
		return (int) getCurrentSession().save(caja); 
	}

	public void updateCaja(Caja caja) {
		getCurrentSession().update(caja);
	}
	
	public Caja getCaja(Long idCaja){
		return (Caja) getCurrentSession().get(Caja.class, idCaja);
	}	

}	