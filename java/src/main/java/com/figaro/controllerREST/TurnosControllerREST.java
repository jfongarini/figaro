package com.figaro.controllerREST;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.figaro.model.Turno;
import com.figaro.service.TurnosService;

@RestController
@RequestMapping(value = "/rest/")
public class TurnosControllerREST {
	
	@Autowired
	@Qualifier("TurnosServiceTransactional")
	private TurnosService service;
	
	
	@RequestMapping(value = "turnos/alta",method=RequestMethod.POST)
    public ResponseEntity<Turno> newTurno(@RequestBody Turno turno) {
		return new ResponseEntity<Turno>(service.saveTurno(turno), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "turnos",method=RequestMethod.GET)
    public ResponseEntity<List<Turno>> getTurnosDelDia(@RequestParam String fecha) {
		return new ResponseEntity<List<Turno>>(service.getTurnosDelDia(fecha), HttpStatus.CREATED);
	}
	
	public TurnosService getService() {
		return service;
	}

	public void setService(TurnosService service) {
		this.service = service;
	}
	
}
