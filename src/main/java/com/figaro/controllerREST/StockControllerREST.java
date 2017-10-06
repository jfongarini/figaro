package com.figaro.controllerREST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.figaro.model.Producto;
import com.figaro.service.ProductosService;

@RestController
@RequestMapping(value = "/rest/stock")
public class StockControllerREST {
	
	@Autowired
	@Qualifier("ProductosServiceTransactional")
	private ProductosService service;
	
	@RequestMapping(value = "/{productoID}",method=RequestMethod.GET,produces="application/json")
    public Producto getProducto ( @PathVariable int productoID) {
        return service.getProducto(productoID);
    }
	
	@RequestMapping(value = "/alta",method=RequestMethod.POST)
    public ResponseEntity<Producto> newProducto(@RequestBody Producto producto) {
		return new ResponseEntity<Producto>(service.saveProducto(producto), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/actualizar/{productoID}",method=RequestMethod.PUT)
    public ResponseEntity<Producto> updateProducto(@RequestBody Producto producto) {
		Producto updated = service.updateProducto(producto);
		return new ResponseEntity<Producto>(updated, HttpStatus.OK);
	}

	@RequestMapping(value = "/todos",method=RequestMethod.GET,produces="application/json")
    public List<Producto> getAllProductos() {
        return service.getAllProductos();
    }
	
	@RequestMapping(value = "/buscar",method=RequestMethod.GET,produces="application/json")
    public List<Producto> getAllProductos(@RequestParam String search) {
        return service.buscar(search);
    }
	
	public ProductosService getService() {
		return service;
	}
	
	public void setService(ProductosService service) {
		this.service = service;
	}
	

}