package com.figaro.service;

import org.apache.log4j.Logger;

import com.figaro.model.Caja;
import com.figaro.repository.CajaRepository;

public class CajaService {

	final static Logger LOGGER = Logger.getLogger(CajaService.class);
	
	private CajaRepository repository;

	public Caja saveCaja (Caja caja) {
		Long id = (long) repository.saveCaja(caja);
		caja.setId(id);	
		return caja;
	}	

	public Caja getCaja(Long idCaja) {
		return repository.getCaja(idCaja);
	}
	
	public Caja updateCaja(Caja caja) {
		Caja old = getCaja(caja.getId());		
		old.update(caja);		
		repository.updateCaja(old);
		return caja;
	}
	
	public CajaRepository getRepository() {
		return repository;
	}
	public void setRepository(CajaRepository repository) {
		this.repository = repository;
	}
	
}