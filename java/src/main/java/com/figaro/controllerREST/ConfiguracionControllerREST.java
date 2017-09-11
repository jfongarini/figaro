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

import com.figaro.model.Ciudad;
import com.figaro.model.Peluquero;
import com.figaro.model.Trabajo;
import com.figaro.service.ConfiguracionService;

@RestController
@RequestMapping(value = "/rest/configuracion")
public class ConfiguracionControllerREST {

	@Autowired
	@Qualifier("ConfiguracionServiceTransactional")
	private ConfiguracionService service;
	
	@RequestMapping(value = "/ciudades",method=RequestMethod.GET)
    public List<Ciudad> getCiudades() {
		return service.getCiudades();
	}
	
	@RequestMapping(value = "/ciudades/alta",method=RequestMethod.POST)
    public ResponseEntity<Ciudad> newCiudad(@RequestBody Ciudad ciudad) {
		Integer newID = service.saveCiudad(ciudad);
		ciudad.setId(newID);
		return new ResponseEntity<Ciudad>(ciudad, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/ciudades/baja/{idCiudad}",method=RequestMethod.DELETE)
    public ResponseEntity<Ciudad> deleteCiudad(@PathVariable Integer idCiudad) {
		service.deleteCiudad(idCiudad);
		return new ResponseEntity<Ciudad>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/trabajos",method=RequestMethod.GET)
    public List<Trabajo> getTrabajos() {
		return service.getTrabajos();
	}
	
	@RequestMapping(value = "/trabajos/alta",method=RequestMethod.POST)
    public ResponseEntity<Trabajo> newTrabajo(@RequestBody Trabajo trabajo) {
		Integer newID = service.saveTrabajo(trabajo);
		trabajo.setId(newID);
		return new ResponseEntity<Trabajo>(trabajo, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/trabajos/baja/{idTrabajo}",method=RequestMethod.DELETE)
    public ResponseEntity<Trabajo> deleteTrabajo(@PathVariable Integer idTrabajo) {
		service.deleteTrabajo(idTrabajo);
		return new ResponseEntity<Trabajo>(HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/peluqueros",method=RequestMethod.GET)
    public List<Peluquero> getPeluqueros() {
		return service.getPeluqueros();
	}
	
	@RequestMapping(value = "/peluqueros/alta",method=RequestMethod.POST)
    public ResponseEntity<Peluquero> newPeluquero(@RequestBody Peluquero peluquero) {
		Integer newID = service.savePeluquero(peluquero);
		peluquero.setId(newID);
		return new ResponseEntity<Peluquero>(peluquero, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/peluqueros/baja/{idPeluquero}",method=RequestMethod.DELETE)
    public ResponseEntity<Peluquero> deletePeluquero(@PathVariable Integer idPeluquero) {
		service.deletePeluquero(idPeluquero);
		return new ResponseEntity<Peluquero>(HttpStatus.OK);
	}
	
	
	
}
