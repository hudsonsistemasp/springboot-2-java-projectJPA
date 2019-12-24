package com.example.courseJPA.entities.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.courseJPA.entities.Order;
import com.example.courseJPA.entities.Product;

//Por ser uma classe auxiliar de chave composta, anotamos com essa do JPA abaixo
@Embeddable
public class OrderItemPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Pedido e produto terão relacionamento muitos para um com OrderItemPK - pedido-produto
	@ManyToOne
	@JoinColumn(name = "product_id")//Nome da chave estrangeira na tabela do banco relacional
	private Product product;
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	//Essa classe, em especial, não terá o construtor
	
	public void setProduct(Product product) {
		this.product = product;
	}
	public Product getProduct() {
		return product;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	public Order getOrder() {
		return order;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}
	
	//Para comparar um item de pedido, o que compõe isso são: o pedido e o ítem do pedido
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemPK other = (OrderItemPK) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
	
	
}
