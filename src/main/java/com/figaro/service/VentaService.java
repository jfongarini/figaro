package com.figaro.service;

import java.util.List;
import org.apache.log4j.Logger;
import com.figaro.model.Venta;
import com.figaro.repository.VentaRepository;


public class VentaService {

	final static Logger LOGGER = Logger.getLogger(VentaService.class);
	
	private VentaRepository repository;

	
	public Venta getVenta(int ventaID) {
		LOGGER.debug("Obteniendo la Venta con ID: " + ventaID);
		return repository.getVenta(ventaID);
	}
	
	public Venta saveVenta (Venta venta) {
		LOGGER.info("Guardando la Venta: " + venta.toString());
		int id = repository.saveVenta(venta);
		venta.setId(id);
		return venta;
	}	
		
	public Venta updateVenta(Venta venta) {
		Venta updated = getVenta(venta.getId());
		LOGGER.info("Actualizando la Venta: " + venta.toString());
		updated.update(venta);
		repository.updateVenta(updated);
		return updated;
	}
	
	public Venta deleteVenta(int ventaId) {
		Venta venta = getVenta(ventaId);
		LOGGER.info("Guardando la Venta: " + venta.toString());
		repository.deleteVenta(venta);
		return venta;
	}
	
	public List<Venta> getAllVenta(){
		LOGGER.debug("Obteniendo todas las ventas");
		return repository.getAll();
	}	
	
	public VentaRepository getRepository() {
		return repository;
	}
	public void setRepository(VentaRepository repository) {
		this.repository = repository;
	}
	
	
}
