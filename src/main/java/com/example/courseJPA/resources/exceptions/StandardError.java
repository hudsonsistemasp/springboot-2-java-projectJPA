/*
 O spring está me retornando um objeto Json mostrando os erros e vou personalizar aproveitando os campos e criando uma exceção parecida com essa abaixo. Esse objeto será gerado na camada RESOURCES, por que ele já é um objeto da requisição
{
    "timestamp": "2020-01-06T23:38:17.967+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "No value present",
    "path": "/users/10"
} 
 * */

package com.example.courseJPA.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Notação que garante que o formato DE STRING 8601, seja mostrado no JSON
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
	public StandardError() {
	}
	public StandardError(Instant timestamp, Integer status, String error, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
	}
	
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
	public Instant getTimestamp() {
		return timestamp;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatus() {
		return status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
