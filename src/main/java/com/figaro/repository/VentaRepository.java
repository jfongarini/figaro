package com.figaro.repository;

import java.util.List;
import org.hibernate.query.Query;
import com.figaro.model.Venta;

public class VentaRepository extends AbstractRepository {
	
	public Integer saveVenta(Venta venta) {
		return (Integer) getCurrentSession().save(venta);
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
