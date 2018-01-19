package com.figaro.repository;

import java.util.List;
import org.hibernate.query.Query;
import com.figaro.model.Producto;

public class ProductosRepository extends AbstractRepository {
	
	public Integer saveProducto(Producto producto) {
		return (Integer) getCurrentSession().save(producto);
	}

	public void updateProducto(Producto producto) {
		getCurrentSession().update(producto);
	}
	
	public Producto getProducto (int idProducto) {
		return (Producto) getCurrentSession().get(Producto.class, idProducto);
	}

	public void deleteProducto(Producto producto) {
		getCurrentSession().delete(producto);
	}	
	
	@SuppressWarnings("unchecked")
	public List<Producto> getAll() {
		return getCurrentSession().createQuery("from Producto").list();		
	}

	@SuppressWarnings("unchecked")
	public List<Producto> buscar(String search) {
		Query<Producto> query = getCurrentSession().createQuery("FROM Producto p WHERE p.nombre LIKE CONCAT('%',?1,'%') OR p.descripcion LIKE CONCAT ('%',?1,'%')");
	    query.setParameter(1, search);
	    return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Producto> buscarFaltante() {
		//Query<Producto> query = getCurrentSession().createQuery("FROM Producto p WHERE p.cantidad < p.cantidadMinima");
		//query.setParameter(1,4);
		//return query.getResultList();
		return getCurrentSession().createQuery("FROM Producto p WHERE p.cantidad < p.cantidadMinima").list();
	
	
	}	
	
	
}
