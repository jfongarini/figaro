package com.figaro.service;


import java.util.List;

import org.apache.log4j.Logger;

import com.figaro.model.Categoria;
import com.figaro.model.Ciudad;
import com.figaro.model.Peluquero;
import com.figaro.model.Trabajo;
import com.figaro.repository.ConfiguracionRepository;

public class ConfiguracionService {
	
	final static Logger LOGGER = Logger.getLogger(ConfiguracionService.class);

	private ConfiguracionRepository repository;
	
	public Integer saveCiudad(Ciudad ciudad) {
		LOGGER.info("Guardando la ciudad: "+ ciudad.toString());
		return repository.saveCiudad(ciudad);
	}	
	
	public void deleteCiudad(Integer idCiudad) {
		LOGGER.info("Eliminando la ciudad con ID: " + idCiudad);
		repository.deleteCiudad(idCiudad);
	}
	
	public Integer saveTrabajo(Trabajo trabajo) {
		LOGGER.info("Guardando el trabajo: "+ trabajo.toString());
		return repository.saveTrabajo(trabajo);
	}
	
	public List<Ciudad> getCiudades() {
		LOGGER.debug("Obteniendo todas las ciudades");
		return repository.getCiudades();
	}
	
	public void deleteTrabajo(Integer idTrabajo) {
		LOGGER.info("Eliminando el trabajo con ID: " + idTrabajo);
		repository.deleteTrabajo(idTrabajo);		
	}

	public List<Trabajo> getTrabajos() {
		LOGGER.debug("Obteniendo todos los trabajos");
		return repository.getTrabajos();
	}

	public Trabajo getTrabajo(Integer idTrabajo) {
		LOGGER.debug("Obteniendo el trabajo con ID: " + idTrabajo);
		return repository.getTrabajo(idTrabajo);
	}
	
	public Trabajo updateTrabajo(Trabajo trabajo) {
		LOGGER.info("Actualizando el trabajo con ID: " + trabajo.getId()+" con:"+ trabajo.toString());
		Trabajo repoTrabajo = getTrabajo(trabajo.getId());
		repoTrabajo.setDescripcion(trabajo.getDescripcion());
		repoTrabajo.setPrecio(trabajo.getPrecio());
		saveTrabajo(repoTrabajo);
		return repoTrabajo;
	}
	
	public List<Trabajo> buscar(String search) {
		return repository.buscar(search);
	}
	
	public Integer savePeluquero(Peluquero peluquero) {
		LOGGER.info("Guardando el peluquero: "+ peluquero.toString());
		return repository.savePeluquero(peluquero);
	}

	public void deletePeluquero(Integer idPeluquero) {
		LOGGER.info("Eliminando el peluquero con ID: " + idPeluquero);
		repository.deletePeluquero(idPeluquero);
	}
	
	public List<Peluquero> getPeluqueros() {
		LOGGER.debug("Obteniendo todos los peluqueros");
		return repository.getPeluqueros();
	}
	
	public List<Categoria> getCategorias() {
		LOGGER.debug("Obteniendo todas las categorias");
		return repository.getCategorias();
	}

	public Integer save(Categoria categoria) {
		LOGGER.info("Guardando la categoria: "+ categoria.toString());
		return repository.saveCategoria(categoria);
	}

	public void deleteCategoria(Integer idCategoria) {
		LOGGER.info("Eliminando la categoria con ID: " + idCategoria);
		repository.deleteCategoria(idCategoria);
	}
	
	public ConfiguracionRepository getRepository() {
		return repository;
	}

	public void setRepository(ConfiguracionRepository repository) {
		this.repository = repository;
	}




}
