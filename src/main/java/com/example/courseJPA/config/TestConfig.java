package com.example.courseJPA.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.courseJPA.entities.Order;
import com.example.courseJPA.entities.User;
import com.example.courseJPA.entities.enums.OrderStatus;
import com.example.courseJPA.repositories.OrderRepository;
import com.example.courseJPA.repositories.UserRepository;

//1-Para falar para o Spring que é uma classe de configuração usa essa notação abaixo:
@Configuration

/*2-Para apontar para o arquivo na pasta src/main/resources/application.properties
  e dizer que é uma configuração específica para o perfil de teste 
  e atentar-se para  colocar o mesmo nome "test" que está no parâmetro do arquivo usa essa notação abaixo:*/
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	/*3-Agora fazer uma injeção de dependência com a classe UserRepository, pois essa que é responsável
	  por fazer as operações com a entidade do contexto, no caso, User. Usa essa notação abaixo:*/
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	
	/*4-Agora como vou fazer pra que isso aqui seja iniciado quando o programa for executado?
	  Várias formas de fazer no spring, mas usaremos a implementação da interface CommandLineRunner 
	  Tudo que estiver dentro desse método, será executado na inicialização do programa*/
	
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("Maria Brown", "maria@gmail.com", "988888888", "123456");
		User user2 = new User("Alex Green", "alex@gmail.com", "977777777", "123456");
		User user3 = new User("James Brown", "jms@gmail.com", "966666666", "123456");
		
		//5-Agora chamo quem é responsável pelas operações com a entidade 
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.DELIVERED, user1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.PAID, user3);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, user2);
		Order o4 = new Order(null, Instant.parse("2019-10-10T12:21:00Z"), OrderStatus.CANCELED, user2);
		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3, o4));
		
	}	
	
}
