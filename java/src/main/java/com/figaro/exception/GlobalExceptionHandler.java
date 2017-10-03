package com.figaro.exception;

import org.springframework.http.HttpStatus;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice  
@RestController  
public class GlobalExceptionHandler {  
  
    private static final String MSG_DUPLICADO = "Ya existe un elemento con estos datos";
    private static final String MSG_TURNO_DUPLICADO = "Ya existe un turno en esa franja horaria para ese peluquero";
    private static final String MSG_HORARIO_INVALIDO = "El horario seleccionado es invalido";

	@ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  
    public ApiError handleException(Exception e){
    	return new ApiError(HttpStatus.BAD_REQUEST, MSG_DUPLICADO);
    }  
	
	@ExceptionHandler(value = TurnoOcupadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  
    public ApiError turnoOcupado(Exception e){
    	return new ApiError(HttpStatus.BAD_REQUEST, MSG_TURNO_DUPLICADO);
    }
	
	
	@ExceptionHandler(value = HorarioInvalidoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  
    public ApiError horarioInvalido(Exception e){
    	return new ApiError(HttpStatus.BAD_REQUEST, MSG_HORARIO_INVALIDO);
    }
	
  
  
}  