package com.figaro.service;

import java.util.List;
import org.apache.log4j.Logger;
import com.figaro.model.Item;
import com.figaro.model.Producto;
import com.figaro.model.Venta;
import com.figaro.repository.VentaRepository;
import com.figaro.service.ProductosService;



public class VentaService {

	final static Logger LOGGER = Logger.getLogger(VentaService.class);
	
	private VentaRepository repository;
	private ProductosService productosService;
	
	public Venta getVenta(int ventaID) {
		LOGGER.debug("Obteniendo la Venta con ID: " + ventaID);
		return repository.getVenta(ventaID);
	}
	
	public Venta saveVenta (Venta venta) {
		LOGGER.info("Guardando la Venta: " + venta.toString());
		Integer newId = repository.saveVenta(venta);
		venta.setId(newId);
		LOGGER.info("La venta se guardó correctamente");
		actualizarStock(venta.getItems());
		return venta;
	}

	public void actualizarStock(List<Item> items) {
		LOGGER.info("Actualizando stock...");
		int newCantidad;
		Producto p;
		
		for(int i = 0; i < items.size(); i++) {
	        p = productosService.buscarDesdeVenta(items.get(i).getNombreProducto(), items.get(i).getDescripcionProducto());
	        LOGGER.info("Se va a actualizar stock del Producto: " + p.toString());
	        newCantidad = (p.getCantidad()-items.get(i).getCantidad());
	        p = productosService.updateCantidad(p.getId(), newCantidad);
		}	
	}

	public Integer createItem (Item item) {	
		return repository.saveItem(item);
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

	public ProductosService getProductosService() {
		return productosService;
	}

	public void setProductosService(ProductosService productosService) {
		this.productosService = productosService;
	}
	
}
