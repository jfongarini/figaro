package com.figaro.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientesController {
	
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		return "cliente";
	}

	
	
	
}
