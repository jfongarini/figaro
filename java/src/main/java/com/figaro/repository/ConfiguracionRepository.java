package com.figaro.repository;

import java.util.List;

import com.figaro.model.Ciudad;
import com.figaro.model.Peluquero;
import com.figaro.model.Trabajo;


@SuppressWarnings("unchecked")
public class ConfiguracionRepository extends AbstractRepository {

	public Integer saveCiudad (Ciudad ciudad) {
		return (Integer) getCurrentSession().save(ciudad); 
	}

	public void deleteCiudad(Integer idCiudad) {
		Ciudad toDelte = getCurrentSession().load(Ciudad.class, idCiudad);
		getCurrentSession().delete(toDelte);
	}
	
	public List<Ciudad> getCiudades() {
		return getCurrentSession().createQuery("from Ciudad").list();
	}
	
	public Integer saveTrabajo(Trabajo trabajo) {
		return (Integer) getCurrentSession().save(trabajo);
	}

	public void deleteTrabajo(Integer idTrabajo) {
		Trabajo toDelte = getCurrentSession().load(Trabajo.class, idTrabajo);
		getCurrentSession().delete(toDelte);
	}
	
	public List<Trabajo> getTrabajos() {
		return getCurrentSession().createQuery("from Trabajo").list();
	}
	
	public Trabajo getTrabajo(Integer idTrabajo) {
		return getCurrentSession().get(Trabajo.class, idTrabajo);
		
	}

	public Integer savePeluquero(Peluquero peluquero) {
		return (Integer) getCurrentSession().save(peluquero);
	}

	public void deletePeluquero(Integer idPeluquero) {
		Peluquero toDelte = getCurrentSession().load(Peluquero.class, idPeluquero);
		getCurrentSession().delete(toDelte);
	}

	public List<Peluquero> getPeluqueros() {
		return getCurrentSession().createQuery("from Peluquero").list();
	}

	
}
