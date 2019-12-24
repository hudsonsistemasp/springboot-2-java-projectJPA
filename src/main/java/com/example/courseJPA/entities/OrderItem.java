package com.example.courseJPA.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.courseJPA.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable{
	private static final long serialVersionUID = 1L;

	//Colocar o (id) identificador dessa classe, que foi construido na classe OrderItemPK
	@EmbeddedId
	private OrderItemPK id = new OrderItemPK();//E é através desse id que vamos correlacionar o produto e pedido aqui dentro, no construtor
	//Toda vez que eu criar uma classe uma classe auxiliar com chave composta, não esquecer de instanciar para não vir NULLPOINTER
	
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
	}
	
	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		this.id.setProduct(product);
		this.id.setOrder(order);
		this.quantity = quantity;
		this.price = price;
	}
	
	@JsonIgnore //Para não dar bug no Jackson que serializa o JSON da requisição
	//Para acesso ao objeto OrderItemPK
	public Order getOrder() {
		return id.getOrder();
	}
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	public Product getProduct() {
		return id.getProduct();
	}
	public void setProduct(Product product) {
		id.setProduct(product);
	}
	//
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	//o ID QUEM identifica esse pedido
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
