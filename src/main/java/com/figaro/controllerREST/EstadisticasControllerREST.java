package com.figaro.controllerREST;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.figaro.model.Cliente;
import com.figaro.model.Ciudad;
import com.figaro.service.EstadisticasService;

@RestController
@RequestMapping(value = "/rest/")
public class EstadisticasControllerREST {
	
	@Autowired
	@Qualifier("EstadisticasServiceTransactional")
	private EstadisticasService service;
	
	@RequestMapping(value = "estadisticas/buscarClienteSexo",method=RequestMethod.GET,produces="application/json")
    public ResponseEntity<List<Cliente>> searchClienteSexo(@RequestParam String search) {
		return new ResponseEntity<List<Cliente>>(service.buscarClienteSexo(search), HttpStatus.OK);
    }	
	
	@RequestMapping(value = "estadisticas/ciudad",method=RequestMethod.GET,produces="application/json")
    public ResponseEntity<List<Ciudad>> getCiudades() {
        return new ResponseEntity<List<Ciudad>>(service.getCiudades(), HttpStatus.OK);
    }
	
	@RequestMapping(value = "estadisticas/clientesCiudad",method=RequestMethod.GET,produces="application/json")
    public ResponseEntity<Map<String, Integer>> buscarClienteCiudad() {
        return new ResponseEntity<Map<String, Integer>>(service.buscarClienteCiudad(), HttpStatus.OK);
    }
	
	
	public EstadisticasService getService() {
		return service;
	}
	
	public void setService(EstadisticasService service) {
		this.service = service;
	}
}