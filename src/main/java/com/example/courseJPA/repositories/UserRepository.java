package com.example.courseJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.courseJPA.entities.User;

/*Essa notação é para registrar como um componente do spring e assim ser usado para uma injeçao de dependência
  que no caso, como ex, a camada service da classe UserService está usando como dependência. Nesse caso a notação
  @Repository é opcional, pois a interface JpaRepository já está registrada como componente no Spring */

@Repository

/*Uma interface repository é responsável por fazer operações com a classe que ela aponta, User,(dependência)
  Basta extender à classe JpaRepsoitory passando como argumento a classe e o tipo da chave de acesso(Id)*/
public interface UserRepository extends JpaRepository<User,Long>{
	/*Como é uma interface, teremos várias operações para trabalhar com o User, mas pensamos que 
	 por ser esse tipo interface teremos que implementar vários métodos. Mas só de passarmos os argumentos
	 User e long(entidade, tipo da chave) o spring data JPA já interpreta como implementação padrão. */
	
	
	
	
	
}
