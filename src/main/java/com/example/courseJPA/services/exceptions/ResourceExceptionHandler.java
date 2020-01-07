package com.example.courseJPA.services.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.courseJPA.resources.exceptions.StandardError;

//Nessa classe que daremos o tratamento manual para os erros - TRATAMENTO PERSONALIZADO

@ControllerAdvice //intercepta as exceções que acontecerem para que esse objeto possa executar o possível tratamento
public class ResourceExceptionHandler {
	
	//Notation que é capaz de interceptar a requisição que deu a exceção com o parâmetro da classe que pega a exceção
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException ex, HttpServletRequest request){
		String error = "Resource not Found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, ex.getMessage(), request.getRequestURI());
		//Para retornar uma resposta com o código personalizado, usamos o .status
		return ResponseEntity.status(status).body(err);
	}
}







