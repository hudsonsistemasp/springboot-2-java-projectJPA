package com.example.courseJPA.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_order") /*Deu erro quando subi a applicação, pois quando vai criar a tabela pelo JPA 
							com o nome da classe sendo Order, entra em conflito com a palavra reservada do SQL
							Dai usamos essa notação que a tabela seja criada com o que está no parâmetro name*/
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Instant moment;
	
	//O JPA precisa transformar a associação abaixo em chaves estrangeiras no banco de dados
	// Na UML indica que o lado do PEDIDOS(ORDERS) são muitos e no lado do USER(CLIENT) é 1. Notação: @ManyToOne 
	@ManyToOne 
	@JoinColumn(name = "client_id") //Nome da chave estrangeira no banco de dados com client
	@JsonIgnore
	private User client;
	
	public Order() {
		super();
	}

	public Order(Integer id, Instant moment, User client) {
		super();
		this.id = id;
		this.moment = moment;
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
	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
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
