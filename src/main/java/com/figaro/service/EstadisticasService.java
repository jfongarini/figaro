package com.figaro.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.figaro.model.Cliente;
import com.figaro.model.Movimiento;
import com.figaro.repository.EstadisticasRepository;

public class EstadisticasService {
	
	final static Logger LOGGER = Logger.getLogger(EstadisticasService.class);
	
	private EstadisticasRepository repository;
	private ClientesService clientesService;
	private MovimientosService movimientosService;
	
	public Map<String, Integer> buscarClienteCiudad(){
		List<Cliente> allClientes = clientesService.getAllClientes();
		Map<String, Integer> mapClientes = new HashMap<String, Integer>();
		for (Cliente cliente : allClientes) {
			String ciudad = cliente.getDirCiudad();
			Integer cantidadHabitantes = mapClientes.get(ciudad);
			if (cantidadHabitantes == null) {
				mapClientes.put(ciudad, 1);
			} else {
				cantidadHabitantes ++;
				mapClientes.put(ciudad, cantidadHabitantes);
			}
		}
		return mapClientes;
	}
	
	public Map<String, Integer> buscarClienteSexo(){
		List<Cliente> allClientes = clientesService.getAllClientes();
		Map<String, Integer> mapClientes = new HashMap<String, Integer>();
		for (Cliente cliente : allClientes) {
			String sexo = cliente.getSexo();
			Integer cantidadHabitantes = mapClientes.get(sexo);
			if (cantidadHabitantes == null) {
				mapClientes.put(sexo, 1);
			} else {
				cantidadHabitantes ++;
				mapClientes.put(sexo, cantidadHabitantes);
			}
		}
		return mapClientes;
	}
	
	public Map<String, Integer> buscarProductoMasVendido() throws ParseException{

		String category = "Ventas";

		String oldstring2 = "2017-12-01";
		Date from = new SimpleDateFormat("yyyy-MM-dd").parse(oldstring2);	
		
		String oldstring3 = "2017-12-31";
		Date to = new SimpleDateFormat("yyyy-MM-dd").parse(oldstring3);	
		
		List<Movimiento> searchMovimientos = movimientosService.searchMovimientos(from, to, category);
		Map<String, Integer> mapMovimientos = new HashMap<String, Integer>();
		for (Movimiento movimiento : searchMovimientos) {
			String producto = movimiento.getDetalle();
			Integer cantidadVentas = mapMovimientos.get(producto);
			if (cantidadVentas == null) {
				mapMovimientos.put(producto, 1);
			} else {
				cantidadVentas ++;
				mapMovimientos.put(producto, cantidadVentas);
			}
		}
		return mapMovimientos;
	}

	public EstadisticasRepository getRepository() {
		return repository;
	}

	public void setRepository(EstadisticasRepository repository) {
		this.repository = repository;
	}

	public ClientesService getClientesService() {
		return clientesService;
	}

	public void setClientesService(ClientesService clientesService) {
		this.clientesService = clientesService;
	}

	public MovimientosService getMovimientosService() {
		return movimientosService;
	}

	public void setMovimientosService(MovimientosService movimientosService) {
		this.movimientosService = movimientosService;
	}
	
}