package com.figaro.repository;

import com.figaro.model.Producto;

public class ProductosRepository extends AbstractRepository {
	
	public int saveProducto(Producto producto) {
		return (int) getCurrentSession().save(producto);
	}

	public Producto getProducto (int idProducto) {
		return getCurrentSession().get(Producto.class, idProducto);
	}
	
	
}
