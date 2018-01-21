package com.figaro.service;

import static com.figaro.util.Constants.DATE_TIME_FORMAT;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.figaro.model.Cliente;
import com.figaro.model.Movimiento;
import com.figaro.model.Peluquero;
import com.figaro.model.Turno;
import com.figaro.repository.EstadisticasRepository;

public class EstadisticasService {
	
	final static Logger LOGGER = Logger.getLogger(EstadisticasService.class);
	
	private EstadisticasRepository repository;
	private ClientesService clientesService;
	private MovimientosService movimientosService;
	private TurnosService turnosService;
	@JsonFormat(pattern=DATE_TIME_FORMAT)
	private Date pruebaHasta;
	
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
	
	public Map<String, Integer> buscarProductoMasVendido(Date from, Date to) throws ParseException{

		String category = "Ventas";
		
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
	
	public Map<String, BigDecimal> buscarTotalesDeCaja(Date from, Date to) throws ParseException{

		String category = "";
		
		List<Movimiento> searchMovimientos = movimientosService.searchMovimientos(from, to, category);
		Map<String, BigDecimal> mapMovimientos = new HashMap<String, BigDecimal>();
		BigDecimal suma = new BigDecimal(0);
		for (Movimiento movimiento : searchMovimientos) {
			String categoria = movimiento.getCategoria();
			BigDecimal cantidadVentas = mapMovimientos.get(categoria);			 			
			if (cantidadVentas == null) {
				mapMovimientos.put(categoria, new BigDecimal(1));
				suma = new BigDecimal(0);
			}else {
				suma = mapMovimientos.get(categoria);
			}
			BigDecimal precio = movimiento.getPrecio();
			if (movimiento.getIsGasto()) {
				suma = suma.subtract(precio);
			} else {
				suma = suma.add(precio);
			}			
			mapMovimientos.put(categoria, suma);
		}
		return mapMovimientos;
	}
	
	public Map<String, BigDecimal> buscarTurnosPorPeluqueroIngreso(Date from, Date to) throws ParseException{
		
		List<Turno> searchTurnos = repository.searchBetween (from,to);
		Map<String, BigDecimal> mapTurnos = new HashMap<String, BigDecimal>();
		BigDecimal suma = new BigDecimal(0);
		for (Turno turnos : searchTurnos) {
			Peluquero peluquero = turnos.getPeluquero();
			String nombreApellido = peluquero.getNombre() + ' ' + peluquero.getApellido();			
			BigDecimal cantidadTurnos = mapTurnos.get(nombreApellido);			 			
			if (cantidadTurnos == null) {
				mapTurnos.put(nombreApellido, new BigDecimal(1));
				suma = new BigDecimal(0);
			}else {
				suma = mapTurnos.get(nombreApellido);
			}			
			BigDecimal precio = turnos.calculatePrecio();	
			
			suma = suma.add(precio);						
			mapTurnos.put(nombreApellido, suma);
		}
		return mapTurnos;
	}
	
	public Map<String, Integer> buscarTurnosPorPeluqueroCant(Date from, Date to) throws ParseException{
		
		List<Turno> searchTurnos = repository.searchBetween (from,to);
		Map<String, Integer> mapTurnos = new HashMap<String, Integer>();		
		for (Turno turnos : searchTurnos) {
			Peluquero peluquero = turnos.getPeluquero();
			String nombreApellido = peluquero.getNombre() + ' ' + peluquero.getApellido();			
			Integer cantidadTurnos = mapTurnos.get(nombreApellido);
			if (cantidadTurnos == null) {
				mapTurnos.put(nombreApellido, 1);
			} else {
				cantidadTurnos ++;
				mapTurnos.put(nombreApellido, cantidadTurnos);
			}
		}
		return mapTurnos;
	}
	
	public TreeMap<String, Integer> buscarTurnoMasSolicitado(Date from, Date to) throws ParseException{
		
		List<Turno> searchTurnos = repository.searchBetween (from,to);
		Map<String, Integer> mapTurnos = new HashMap<String, Integer>();
		DateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
		
		for (Turno turno : searchTurnos) {
			
			Date desde = turno.getDesde();
			Date hasta = turno.getHasta();			
			String fechaSuma = fechaString(desde);			
			
			for (int i=0; i<1;) {
				Integer cantidadTurnos = mapTurnos.get(fechaSuma);
				if (cantidadTurnos == null) {
					mapTurnos.put(fechaSuma, 1);
				} else {
					cantidadTurnos ++;
					mapTurnos.put(fechaSuma, cantidadTurnos);
				}
				Date fechaFormat = format.parse(fechaSuma);
				fechaSuma = fechaSumaQuince(fechaFormat);
				this.pruebaHasta = fechaFormat;				
				if (this.pruebaHasta.compareTo(hasta) == 0) {
					i=1;
				}					
			}
		}
		
		//Ordenar
		TreeMap<String, Integer> sorted = new TreeMap<>();		 
	    sorted.putAll(mapTurnos);
	    for (@SuppressWarnings("unused") Map.Entry<String, Integer> entry : sorted.entrySet()) ;
	
		return sorted;
	}
	
	public String fechaSumaQuince(Date fecha) {
		final long ONE_MINUTE_IN_MILLIS=60000;
        Calendar date = Calendar.getInstance();
        date.setTime(fecha);
        long t= date.getTimeInMillis();		        
        Date afterAddingTenMins=new Date(t + (15 * ONE_MINUTE_IN_MILLIS));	       
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        String strDate = sdf.format(afterAddingTenMins);
        return strDate;
	}
	
	public String fechaString(Date fecha) {		
        Calendar date = Calendar.getInstance();
        date.setTime(fecha);
        long t= date.getTimeInMillis();		        
        Date afterAddingTenMins=new Date(t);	       
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        String strDate = sdf.format(afterAddingTenMins);
        return strDate;
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

	public TurnosService getTurnosService() {
		return turnosService;
	}

	public void setTurnosService(TurnosService turnosService) {
		this.turnosService = turnosService;
	}
	
}