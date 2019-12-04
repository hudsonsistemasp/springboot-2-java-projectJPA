package com.example.courseJPA.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.courseJPA.entities.User;

@RestController
@RequestMapping("/users")
public class UserResource {
	//O tipo de retorno ResponseEntity, é um tipo específico do spring para retornar respostas de requisições web
	@GetMapping //Tipo GET do HTTP
	public ResponseEntity<User> findAll(){
		User user = new User("Hudson","email@email.com","988888888", "123456");
		return ResponseEntity.ok().body(user);
	}
}
