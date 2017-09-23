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
import org.springframework.web.bind.annotation.RestController;

import com.figaro.model.Movimiento;
import com.figaro.service.MovimientosService;

@RestController
@RequestMapping(value = "/rest/")
public class MovimientosControllerREST {
	
	@Autowired
	@Qualifier("MovimientosServiceTransactional")
	private MovimientosService service;
	
	@RequestMapping(value = "movimientos",method=RequestMethod.GET,produces="application/json")
    public List<Movimiento> getAllMovimiento() {
        return service.getAllMovimiento();
    }
	
	@RequestMapping(value = "movimientos/{movimientosID}",method=RequestMethod.GET,produces="application/json")
    public Movimiento getMovimiento( @PathVariable Long movimientoID) {
        return service.getMovimiento(movimientoID);
    }
	
	@RequestMapping(value = "movimientos/alta",method=RequestMethod.POST)
    public ResponseEntity<Movimiento> newMovimiento(@RequestBody Movimiento movimiento) {
		return new ResponseEntity<Movimiento>(service.saveMovimiento(movimiento), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "movimientos/actualizar/{movimientoID}",method=RequestMethod.PUT)
    public ResponseEntity<Movimiento> updateMovimiento(@RequestBody Movimiento movimiento) {
		Movimiento updated = service.updateMovimiento(movimiento);
		return new ResponseEntity<Movimiento>(updated, HttpStatus.OK);
	}
	
	public MovimientosService getService() {
		return service;
	}
	
	public void setService(MovimientosService service) {
		this.service = service;
	}
}