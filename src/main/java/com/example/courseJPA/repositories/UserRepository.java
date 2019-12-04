package com.example.courseJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.courseJPA.entities.User;

/*Uma interface repository é responsável por fazer operações com a classe que ela aponta, User,(dependência)
  Basta extender à classe JpaRepsoitory passando como argumento, a classe e o tipo da chave de acesso(Id)*/
public interface UserRepository extends JpaRepository<User,Long>{
	/*Como é uma interface, teremos várias operações para trabalhar com o User, mas pensamos que 
	 por ser esse tipo interface teremos que implementar vários métodos. Mas só de passarmos os argumentos
	 User e long(entidade, tipo da chave) o spring data JPA já interpreta como implementação padrão. */
	
	
	
	
	
}
