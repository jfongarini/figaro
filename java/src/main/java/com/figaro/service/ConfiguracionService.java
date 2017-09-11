package com.figaro.service;


import java.util.List;

import com.figaro.model.Ciudad;
import com.figaro.model.Peluquero;
import com.figaro.model.Trabajo;
import com.figaro.repository.ConfiguracionRepository;

public class ConfiguracionService {

	private ConfiguracionRepository repository;
	
	public Integer saveCiudad(Ciudad ciudad) {
		return repository.saveCiudad(ciudad);
	}	
	
	public void deleteCiudad(Integer idCiudad) {
		 repository.deleteCiudad(idCiudad);
	}
	
	public Integer saveTrabajo(Trabajo trabajo) {
		return repository.saveTrabajo(trabajo);
	}
	
	public List<Ciudad> getCiudades() {
		return repository.getCiudades();
	}
	
	public void deleteTrabajo(Integer idTrabajo) {
		repository.deleteTrabajo(idTrabajo);		
	}

	public List<Trabajo> getTrabajos() {
		return repository.getTrabajos();
	}

	public Integer savePeluquero(Peluquero peluquero) {
		return repository.savePeluquero(peluquero);
	}

	public void deletePeluquero(Integer idPeluquero) {
		repository.deletePeluquero(idPeluquero);
	}
	
	public List<Peluquero> getPeluqueros() {
		return repository.getPeluqueros();
	}
	
	public ConfiguracionRepository getRepository() {
		return repository;
	}

	public void setRepository(ConfiguracionRepository repository) {
		this.repository = repository;
	}

	
}
