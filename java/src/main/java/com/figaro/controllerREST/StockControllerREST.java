package com.figaro.controllerREST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.figaro.model.Producto;
import com.figaro.service.ProductosService;

@RestController
@RequestMapping(value = "/rest/stock")
public class StockControllerREST {
	
	private ProductosService service;
	
	@RequestMapping(value = "/alta",method=RequestMethod.POST)
    public ResponseEntity<Producto> newCliente(@RequestBody Producto producto) {
		return new ResponseEntity<Producto>(service.saveProducto(producto), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{productoID}",method=RequestMethod.GET,produces="application/json")
    public Producto getProducto ( @PathVariable int productoID) {
        return service.getProducto(productoID);
    }
	
	

}