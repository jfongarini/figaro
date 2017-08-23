package com.figaro.controllerREST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.figaro.model.Cliente;
import com.figaro.service.ClientesService;

@RestController
@RequestMapping(value = "/rest/clientes")
public class ClientesControllerREST {
	
	@Autowired
	@Qualifier("ClientesServiceTransactional")
	private ClientesService service;
	
	@RequestMapping(value = "/{clienteID}",method=RequestMethod.GET,produces="application/json")
    public Cliente getCliente( @PathVariable int clienteID) {
        return service.getCliente(clienteID);
    }
	
	@RequestMapping(value = "/alta",method=RequestMethod.POST)
    public void newCliente(@RequestBody Cliente cliente) {
		service.saveCliente(cliente);
	}

	@RequestMapping(value = "/todos",method=RequestMethod.GET,produces="application/json")
    public List<Cliente> getAllClientes() {
        return service.getAll();
    }
	
	
	public ClientesService getService() {
		return service;
	}
	public void setService(ClientesService service) {
		this.service = service;
	}

	
}
