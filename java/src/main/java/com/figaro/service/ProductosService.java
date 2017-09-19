package com.figaro.service;

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
	
	public ProductosRepository getRepository() {
		return repository;
	}
	public void setRepository(ProductosRepository repository) {
		this.repository = repository;
	}
	
}
