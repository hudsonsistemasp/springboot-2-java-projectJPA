package com.example.courseJPA.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.courseJPA.entities.User;
import com.example.courseJPA.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	/*O tipo de retorno ResponseEntity, é um tipo específico do spring para retornar respostas de requisições web.
	  Nesse caso o tipo de retorno da função é uma lista, por que a dependência é da camada service que retorna 
	  uma lista de usuários  */
	
	@GetMapping //Tipo GET do HTTP
	public ResponseEntity<List<User>> findAll(){
		List<User> listUser = userService.findAll();
		return ResponseEntity.ok().body(listUser);
	}
	
	//Vai indicar que a uri vai aceitar um parâmetro na requisição
	@GetMapping(value = "/{id}") // Para que esse parâemtro vá para o controlador do endpoint, coloca a notação @PathVarialble
	public ResponseEntity<User> findById(@PathVariable Long id){
		User user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User user){//Para dizer que esse objeto vai chegar no modo json e esse vai ser deserializado para o objeto User, tenho que colocar a notation @RequestBody
		user = userService.insert(user);
		//return ResponseEntity.ok().body(user);//Retorna cod 200
		//para retornar um cod 201 com o endereço do novo recurso que eu inseri, preciso retornar um object do tipo LOCATION
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		userService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
}










