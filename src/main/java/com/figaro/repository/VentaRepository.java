package com.figaro.repository;

import java.util.List;

import com.figaro.model.Item;
import com.figaro.model.Venta;


public class VentaRepository extends AbstractRepository {
	
	public Integer saveVenta(Venta venta) {
		return (Integer) getCurrentSession().save(venta);
	}

	public Integer saveItem(Item item) {
		return (Integer) getCurrentSession().save(item);
	}
	
	public void updateVenta(Venta venta) {
		getCurrentSession().update(venta);
	}
	
	public Venta getVenta (int idVenta) {
		return (Venta) getCurrentSession().get(Venta.class, idVenta);
	}

	public void deleteVenta(Venta venta) {
		getCurrentSession().delete(venta);
	}	
	
	@SuppressWarnings("unchecked")
	public List<Venta> getAll() {
		return getCurrentSession().createQuery("from Venta").list();		
	}
	
	
}
