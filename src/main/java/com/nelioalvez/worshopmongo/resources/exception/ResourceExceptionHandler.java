package com.nelioalvez.worshopmongo.resources.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nelioalvez.worshopmongo.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice //indica que essa classe trata possiveis erros na aplicação
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError(
			/*timestamp*/	System.currentTimeMillis(),
			/*status*/      status.value(), //ja converte para numero inteiro com o .value()
			/*error*/		"Não Encontrado",
			/*message*/		e.getMessage(),
			/*path*/		request.getRequestURI()
							);
		return ResponseEntity.status(status).body(error);
	}
}
