package com.figaro.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.query.Query;

import com.figaro.model.Categoria;
import com.figaro.model.Ciudad;
import com.figaro.model.Trabajo;

@SuppressWarnings("unchecked")
public class ConfiguracionRepository extends AbstractRepository {

	final static Logger LOGGER = Logger.getLogger(ConfiguracionRepository.class);
	
	//CIUDADES
	public Integer saveCiudad (Ciudad ciudad) {
		return (Integer) getCurrentSession().save(ciudad); 
	}

	public void deleteCiudad(Integer idCiudad) {
		Ciudad ciudad = getCurrentSession().load(Ciudad.class, idCiudad);
		LOGGER.info("Eliminando la ciudad: "+ ciudad.getNombre());
		getCurrentSession().delete(ciudad);
	}
	
	public List<Ciudad> getCiudades() {
		return getCurrentSession().createQuery("from Ciudad").list();
	}
	
	//TRABAJOS
	public Integer saveTrabajo(Trabajo trabajo) {
		return (Integer) getCurrentSession().save("Trabajo",trabajo);
	}

	public void deleteTrabajo(Integer idTrabajo) {
		Trabajo trabajo = (Trabajo) getCurrentSession().get("Trabajo", idTrabajo);
		LOGGER.info("Eliminando el Trabajo: "+ trabajo.getDescripcion());
		getCurrentSession().delete(trabajo);
	}
	
	public List<Trabajo> getTrabajos() {
		return getCurrentSession().createQuery("from Trabajo").list();
	}
	
	public Trabajo getTrabajo(Integer idTrabajo) {
		return (Trabajo) getCurrentSession().get("Trabajo", idTrabajo);
	}
	
	public List<Trabajo> buscar(String search) {
		Query<Trabajo> query = getCurrentSession().createQuery("FROM Trabajo t WHERE t.descripcion LIKE CONCAT('%',:search,'%')");
		query.setParameter("search", search);
	    return query.getResultList();
	}

	//CATEGORIAS
	public List<Categoria> getCategorias() {
		return getCurrentSession().createQuery("from Categoria").list();
	}

	public Integer saveCategoria(Categoria categoria) {
		return (Integer) getCurrentSession().save(categoria);
	}

	public void deleteCategoria(Integer idCategoria) {
		Categoria categoria = getCurrentSession().load(Categoria.class, idCategoria);
		LOGGER.info("Eliminando la Categoria: "+ categoria.getNombre());
		getCurrentSession().delete(categoria);
	}


}
