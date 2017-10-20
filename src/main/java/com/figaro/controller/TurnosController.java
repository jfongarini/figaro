package com.figaro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TurnosController {

	@RequestMapping("/")
	public String turnos() {
		return "html/turnos/turnos";
	}

	@RequestMapping("/turnos/cliente/{clienteID}")
	public String turnosDeCliente() {
		return "html/turnos/turnos-cliente";
	}

	@RequestMapping("/login")
	public String login() {
		return "html/login/login";
	}
	
	
	
}
