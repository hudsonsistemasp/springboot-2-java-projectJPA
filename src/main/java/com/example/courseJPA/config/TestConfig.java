package com.example.courseJPA.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.courseJPA.entities.Category;
import com.example.courseJPA.entities.Order;
import com.example.courseJPA.entities.OrderItem;
import com.example.courseJPA.entities.Payment;
import com.example.courseJPA.entities.Product;
import com.example.courseJPA.entities.User;
import com.example.courseJPA.entities.enums.OrderStatus;
import com.example.courseJPA.repositories.CategoryRepository;
import com.example.courseJPA.repositories.OrderItemRepository;
import com.example.courseJPA.repositories.OrderRepository;
import com.example.courseJPA.repositories.ProductRepository;
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
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	/*4-Agora como vou fazer pra que isso aqui seja iniciado quando o programa for executado?
	  Várias formas de fazer no spring, mas usaremos a implementação da interface CommandLineRunner 
	  Tudo que estiver dentro desse método, será executado na inicialização do programa*/
	
	@Override
	public void run(String... args) throws Exception {
		
		//Criar um produto e persistir no banco de teste H2
		Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
		
		
		//Criar categorias e persistir no banco
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers");
		
		
		//Criando os clientes e persistindo no banco
		User user1 = new User("Maria Brown", "maria@gmail.com", "988888888", "123456");
		User user2 = new User("Alex Green", "alex@gmail.com", "977777777", "123456");
		User user3 = new User("James Brown", "jms@gmail.com", "966666666", "123456");
		
		//5-Agora chamo quem é responsável pelas operações com a entidade
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
		
		//Associando um cliente a um pedido no paradgima orientado a objeto
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.DELIVERED, user1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.PAID, user3);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, user2);
		Order o4 = new Order(null, Instant.parse("2019-10-10T12:21:00Z"), OrderStatus.CANCELED, user2);
		orderRepository.saveAll(Arrays.asList(o1, o2, o3, o4));
		
		//Agora associar um produto a uma categoria, pois é uma relação muitos para muitos
		p1.getCategories().add(cat2);
		p2.getCategories().add(cat1);
		p3.getCategories().add(cat3);
		p4.getCategories().add(cat3);
		p5.getCategories().add(cat2);
		productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		
		//Criar os itens do pedido
		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p4, 2, p4.getPrice());
		OrderItem oi5 = new OrderItem(o4, p2, 2, p2.getPrice());
		OrderItem oi6 = new OrderItem(o3, p5, 4, p5.getPrice());
		orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4,oi5,oi6));
		
		//Agora vou criar um pagamento
		Payment payment = new Payment(null, Instant.parse("2019-07-21T00:42:10Z"), o2);
		/*Agora não mando o repository do payment salvar, mas sim o do order, pois só assim associo
		e o cascade que está na classe Order irá associar o ID dela na ID do pagamento. Tanto que no
		parâmetro do construtor passamos null para que o JPA gerencie isso. */
		
		/*Agora vou associar um pagamento a uma Order e mandar o repository do Order salvar
		  e com isso nascerá a associação dos ID´S*/
		o2.setPayment(payment);
		orderRepository.save(o2);
		
		
	}	
	
}
