package com.figaro.exception;

import org.springframework.http.HttpStatus;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice  
@RestController  
public class GlobalExceptionHandler {  
  
	final static Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);
	
    private static final String MSG_DUPLICADO = "Ya existe un elemento con estos datos";
    private static final String MSG_TURNO_DUPLICADO = "Ya existe un turno en esa franja horaria para ese peluquero";
    private static final String MSG_HORARIO_INVALIDO = "El horario seleccionado es invalido";
    private static final String MSG_DESCUENTO_INVALIDO = "El descuento no puede ser mayor al monto total";

	@ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  
    public ApiError handleException(Exception e){
		LOGGER.error(MSG_DUPLICADO + e.getMessage());
    	return new ApiError(HttpStatus.BAD_REQUEST, MSG_DUPLICADO);
    }  
	
	@ExceptionHandler(value = TurnoOcupadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  
    public ApiError turnoOcupado(Exception e){
		LOGGER.error(MSG_TURNO_DUPLICADO + e.getMessage());
    	return new ApiError(HttpStatus.BAD_REQUEST, MSG_TURNO_DUPLICADO);
    }
	
	
	@ExceptionHandler(value = HorarioInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  
    public ApiError horarioInvalido(Exception e){
		LOGGER.error(MSG_HORARIO_INVALIDO + e.getMessage());
    	return new ApiError(HttpStatus.BAD_REQUEST, MSG_HORARIO_INVALIDO);
    }
	
  
	@ExceptionHandler(value = DescuentoInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  
    public ApiError descuentoInvalido(Exception e){
		LOGGER.error(MSG_DESCUENTO_INVALIDO + e.getMessage());
    	return new ApiError(HttpStatus.BAD_REQUEST, MSG_DESCUENTO_INVALIDO);
    }
	
	
}  