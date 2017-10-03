package com.figaro.controllerREST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.figaro.model.Cliente;
import com.figaro.service.ClientesService;

@RestController
@RequestMapping(value = "/rest/")
public class ClientesControllerREST {
	
	@Autowired
	@Qualifier("ClientesServiceTransactional")
	private ClientesService service;
	
	@RequestMapping(value = "clientes",method=RequestMethod.GET,produces="application/json")
    public List<Cliente> getAllClientes() {
        return service.getAllClientes();
    }
	
	@RequestMapping(value = "clientes/{clienteID}",method=RequestMethod.GET,produces="application/json")
    public Cliente getCliente( @PathVariable int clienteID) {
        return service.getCliente(clienteID);
    }
	
	@RequestMapping(value = "clientes/alta",method=RequestMethod.POST)
    public ResponseEntity<Cliente> newCliente(@RequestBody Cliente cliente) {
		return new ResponseEntity<Cliente>(service.saveCliente(cliente), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "clientes/actualizar/{clienteID}",method=RequestMethod.PUT)
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente) {
		Cliente updated = service.updateCliente(cliente);
		return new ResponseEntity<Cliente>(updated, HttpStatus.OK);
	}

	@RequestMapping(value = "clientes/buscar",method=RequestMethod.GET,produces="application/json")
    public List<Cliente> searchClientes(@RequestParam String search) {
        return service.buscar(search);
    }
	
	public ClientesService getService() {
		return service;
	}
	
	public void setService(ClientesService service) {
		this.service = service;
	}
	
}
