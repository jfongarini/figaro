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

import com.figaro.model.Peluquero;
import com.figaro.service.PeluquerosService;

@RestController
@RequestMapping(value = "/rest")
public class PeluquerosControllerREST {

	@Autowired
	@Qualifier("PeluquerosServiceTransactional")
	private PeluquerosService service;
	
	
	@RequestMapping(value = "/peluqueros",method=RequestMethod.GET)
    public ResponseEntity<List<Peluquero>> getPeluqueros() {
		return new ResponseEntity<List<Peluquero>>(service.getPeluqueros(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/peluqueros/{peluqueroId}",method=RequestMethod.GET)
    public ResponseEntity<Peluquero> getPeluquero(@PathVariable int peluqueroId) {
		return new ResponseEntity<Peluquero>(service.getPeluquero(peluqueroId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/peluqueros/alta",method=RequestMethod.POST)
    public ResponseEntity<Peluquero> addPeluquero(@RequestBody Peluquero peluquero) {
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
