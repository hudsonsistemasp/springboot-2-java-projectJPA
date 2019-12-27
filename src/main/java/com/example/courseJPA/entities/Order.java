package com.example.courseJPA.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.courseJPA.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_order") /*
							 * Deu erro quando subi a applicação, pois quando vai criar a tabela pelo JPA
							 * com o nome da classe sendo Order, entra em conflito com a palavra reservada
							 * do SQL Dai usamos essa notação que a tabela seja criada com o que está no
							 * parâmetro name
							 */
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// Notação que garante que o formato DE STRING 8601, seja mostrado no JSON
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;

	//Vou mudar o tipo de OrderStatus par Integer, para garantir que esse tipo será gravado no banco
	private Integer orderStatus;
	
	// O JPA precisa transformar a associação abaixo em chaves estrangeiras no banco
	// de dados
	// Na UML indica que o lado do PEDIDOS(ORDERS) são muitos e no lado do
	// USER(CLIENT) é 1. Notação: @ManyToOne
	@ManyToOne
	@JoinColumn(name = "client_id") // Nome da chave estrangeira no banco de dados com client
	private User client;

	/*Vou fazer uma associação com a classe OrderItem, para eu ter uma operação que vá buscar
	  os ítens, se é mais de 1 vai ser uma lista, desse Order(pedido)*/
	@OneToMany(mappedBy = "id.order") //Atributo que está na classe OrderItem e que aponta para a OrderItemPK. Agora meus pedidos conhecem os itens dele
	private Set<OrderItem> items = new HashSet<>();
	
	//Vou falar para o meu pedido que ele tem um pagamento
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)//Cascade coloco o mesmo ID do pedido no pagamento para manter esse vínculo
	private Payment payment;
	
	public Order() {
	}

	public Order(Integer id, Instant moment, OrderStatus orderStatus, User client) {
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
		this.client = client;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null)
			this.orderStatus = orderStatus.getCodeEnum();
	}
	
	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}
	
	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public Set<OrderItem> getOrderItens(){
		return items;
	}	
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Double getTotal() {
		Double sum = 0.0;
		for(OrderItem obj : items) {
			//sum += obj.getPrice() * obj.getQuantity();//Basta substituir pelo método denro da classe OrderItem
			sum+= obj.getSubTotal();
		}
		return sum;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
