package com.figaro.service;

import java.util.List;
import org.apache.log4j.Logger;
import com.figaro.model.Producto;
import com.figaro.repository.ProductosRepository;

public class ProductosService {

	final static Logger LOGGER = Logger.getLogger(ProductosService.class);
	
	private ProductosRepository repository;

	public Producto saveProducto (Producto producto) {
		int id = repository.saveProducto(producto);
		producto.setId(id);
		return producto;
	}
	
	public Producto getProducto(int idProducto) {
		return repository.getProducto(idProducto);
	}
	
	public Producto updateProducto(Producto producto) {
		Producto old = getProducto(producto.getId());
		old.setNombre(producto.getNombre());
		old.setCantidad(producto.getCantidad());
		old.setCantidad(producto.getCantidadMin());
		repository.updateProducto(old);
		return producto;
	}
	
	public List<Producto> getAllProductos(){
		return repository.getAll();
	}
	
	public ProductosRepository getRepository() {
		return repository;
	}
	public void setRepository(ProductosRepository repository) {
		this.repository = repository;
	}
	
	public List<Producto> buscar(String search) {
		return repository.buscar(search);
	}
	
}
