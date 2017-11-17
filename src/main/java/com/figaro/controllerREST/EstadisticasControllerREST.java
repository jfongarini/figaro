package com.figaro.controllerREST;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.figaro.service.EstadisticasService;

@RestController
@RequestMapping(value = "/rest/")
public class EstadisticasControllerREST {
	
	@Autowired
	@Qualifier("EstadisticasServiceTransactional")
	private EstadisticasService service;
	
	
	public EstadisticasService getService() {
		return service;
	}
	
	public void setService(EstadisticasService service) {
		this.service = service;
	}
}