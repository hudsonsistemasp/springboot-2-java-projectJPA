package com.example.courseJPA.services.exceptions;

/*RuntimeException = o compilador não me obriga a tratar essa exceção*/
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	//Vou passar o id do recurso que está sendo procurado e não existe no meu banco e subir a msg para a classe mãe
	public ResourceNotFoundException(Object id) {
		super("Resource not found. Id: " + id);
	}
}
