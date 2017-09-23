package com.figaro.controllerREST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.figaro.model.Caja;
import com.figaro.service.CajaService;

@RestController
@RequestMapping(value = "/rest/caja")
public class CajaControllerREST {
	
	@Autowired
	@Qualifier("CajaServiceTransactional")
	private CajaService service;
	
	@RequestMapping(value = "/{cajaID}",method=RequestMethod.GET,produces="application/json")
    public Caja getCaja( @PathVariable Long cajaID) {
        return service.getCaja(cajaID);
    }
	
	@RequestMapping(value = "/alta",method=RequestMethod.POST)
    public ResponseEntity<Caja> newCaja(@RequestBody Caja caja) {
		return new ResponseEntity<Caja>(service.saveCaja(caja), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/actualizar/{cajaID}",method=RequestMethod.PUT)
    public ResponseEntity<Caja> updateCaja(@RequestBody Caja caja) {
		Caja updated = service.updateCaja(caja);
		return new ResponseEntity<Caja>(updated, HttpStatus.OK);
	}
}