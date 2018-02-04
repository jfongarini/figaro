package com.figaro.service;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.figaro.model.Cliente;
import com.figaro.model.Item;
import com.figaro.model.Movimiento;
import com.figaro.model.Venta;
import com.figaro.repository.MovimientosRepository;

public class MovimientosService {

	final static Logger LOGGER = Logger.getLogger(MovimientosService.class);
	
	private MovimientosRepository repository;

	public Movimiento getMovimiento(int movimientoID) {
		LOGGER.debug("Obteniendo el Movimiento con ID: " + movimientoID);
		return repository.getMovimiento(movimientoID);
	}

	public Movimiento saveMovimiento (Movimiento movimiento) {
		LOGGER.info("Guardando el Movimiento: " + movimiento.toString());
		movimiento.descontar();
		int id = repository.saveMovimiento(movimiento);
		movimiento.setId(id);
		return movimiento;
	}	
	
	public Movimiento updateMovimiento(Movimiento movimiento) {
		Movimiento updated = getMovimiento(movimiento.getId());	
		LOGGER.info("Actualizando el Movimiento: " + movimiento.toString());	
		updated.update(movimiento);
		repository.updateMovimiento(updated);
		return updated;
	}
	
	public Movimiento deleteMovimiento(int movimientoID) {
		Movimiento movimiento = getMovimiento(movimientoID);
		LOGGER.info("Eliminando el Movimiento: " + movimiento.toString());
		repository.deleteMovimiento(movimiento);
		return movimiento;
	}
	
	public List<Movimiento> searchMovimientos(Date from, Date to, String category) {		
		return (category.isEmpty()) ? repository.searchBetween (from,to) : repository.searchBetweenWithCategory (from,to,category); 
	}
	
	//public List<String> getItemId(Integer id){
	public List<Item> getItemId(Integer id){
	//	List<String> listItem = new ArrayList<String>();
		Venta laVenta = repository.getVentaId(id);
		List<Item> idItem = laVenta.getItems();		
	/*	for (Item item : idItem) {
			String nom = "Nombre: " + item.getNombreProducto() + ' ' + item.getDescripcionProducto();
			String precioU = "* Precio Unitario: " + item.getPrecioUnitario(); 
			String cant = "* Cantidad: " + item.getCantidad();
			String precioT = "* Precio Total: " + item.getPrecioTotal();
			String texto = nom + precioU + cant + precioT;
			listItem.add(texto);
		} 
	*/
		
		return idItem;
	}
	
	
	public MovimientosRepository getRepository() {
		return repository;
	}
	public void setRepository(MovimientosRepository repository) {
		this.repository = repository;
	}
	
	
	
}