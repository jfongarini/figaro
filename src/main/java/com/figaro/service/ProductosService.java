package com.figaro.service;

import java.util.List;
import org.apache.log4j.Logger;
import com.figaro.model.Producto;
import com.figaro.repository.ProductosRepository;

public class ProductosService {

	final static Logger LOGGER = Logger.getLogger(ProductosService.class);
	
	private ProductosRepository repository;

	
	public Producto getProducto(int productoID) {
		LOGGER.debug("Obteniendo el Producto con ID: " + productoID);
		return repository.getProducto(productoID);
	}
	
	public Producto saveProducto (Producto producto) {
		LOGGER.info("Guardando el Producto con ID: " + producto.getId()+" con:"+ producto.toString());
		int id = repository.saveProducto(producto);
		producto.setId(id);
		return producto;
	}	
		
	public Producto updateProducto(Producto producto) {
		Producto old = getProducto(producto.getId());
		LOGGER.info("Actualizando el Producto con ID: " + old.getId()+" con:"+ producto.toString());
		old.update(producto);
		repository.updateProducto(old);
		return old;
	}
	
	public Producto editProducto(int productoId, int cantidad) {
		Producto old = getProducto(productoId);
		LOGGER.info("Editando el Producto con ID: " + old.getId()+" con:"+ old.toString());
		old.setCantidad(cantidad);
		repository.updateProducto(old);
		return old;
	}	
	
	public Producto deleteProducto(int productoId) {
		Producto producto = getProducto(productoId);
		LOGGER.info("Borrando el Producto con ID: " + productoId +" con:"+ producto.toString());
		repository.deleteProducto(producto);
		return producto;
	}
	
	public List<Producto> getAllProductos(){
		LOGGER.debug("Obteniendo todos los productos");
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
