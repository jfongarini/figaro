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
	
	//CIUDADES
	public Integer saveCiudad(Ciudad ciudad) {
		LOGGER.info("Guardando la ciudad: "+ ciudad.getNombre());
		return repository.saveCiudad(ciudad);
	}	
	
	public void deleteCiudad(Integer idCiudad) {
		repository.deleteCiudad(idCiudad);
	}
	
	public List<Ciudad> getCiudades() {
		LOGGER.debug("Obteniendo todas las ciudades");
		return repository.getCiudades();
	}

	//TRABAJOS
	public Integer saveTrabajo(Trabajo trabajo) {
		LOGGER.info("Guardando el trabajo: "+ trabajo.getDescripcion() +" "+ trabajo.getPrecio());
		return repository.saveTrabajo(trabajo);
	}
	
	public void deleteTrabajo(Integer idTrabajo) {
		repository.deleteTrabajo(idTrabajo);		
	}
	
	public Trabajo getTrabajo(Integer idTrabajo) {
		LOGGER.debug("Obteniendo el trabajo con ID: " + idTrabajo);
		return repository.getTrabajo(idTrabajo);
	}

	public List<Trabajo> getTrabajos() {
		LOGGER.debug("Obteniendo todos los trabajos");
		return repository.getTrabajos();
	}

	public Trabajo updateTrabajo(Trabajo trabajo) {
		LOGGER.info("Actualizando el trabajo: "+ trabajo.getDescripcion() +" "+ trabajo.getPrecio());
		Trabajo repoTrabajo = getTrabajo(trabajo.getId());
		repoTrabajo.setDescripcion(trabajo.getDescripcion());
		repoTrabajo.setPrecio(trabajo.getPrecio());
		saveTrabajo(repoTrabajo);
		LOGGER.info("El trabajo se actualizó correctamente");
		return repoTrabajo;
	}
	
	public List<Trabajo> buscarTrabajos(String search) {
		LOGGER.debug("Buscando trabajos: "+ search);
		return repository.buscar(search);
	}
	
	//PELUQUEROS
	public Integer savePeluquero(Peluquero peluquero) {
		LOGGER.info("Guardando el nuevo peluquero: "+ peluquero.getNombre());
		return repository.savePeluquero(peluquero);
	}

	public void deletePeluquero(Integer idPeluquero) {
		repository.deletePeluquero(idPeluquero);
	}
	
	public List<Peluquero> getPeluqueros() {
		LOGGER.debug("Obteniendo todos los peluqueros");
		return repository.getPeluqueros();
	}
	
	//CATEGORIAS
	public List<Categoria> getCategorias() {
		LOGGER.debug("Obteniendo todas las categorias");
		return repository.getCategorias();
	}

	public Integer save(Categoria categoria) {
		LOGGER.info("Guardando la categoria: "+ categoria.toString());
		return repository.saveCategoria(categoria);
	}

	public void deleteCategoria(Integer idCategoria) {
		repository.deleteCategoria(idCategoria);
	}
	
	
	public ConfiguracionRepository getRepository() {
		return repository;
	}

	public void setRepository(ConfiguracionRepository repository) {
		this.repository = repository;
	}




}
