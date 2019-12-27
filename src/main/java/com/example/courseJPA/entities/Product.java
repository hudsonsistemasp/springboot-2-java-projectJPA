package com.example.courseJPA.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private String imageUrl;
	/*1-O set representa um conjunto e garante que não vou ter um mesmo produto com mais de uma categoria por vez.
	  2-Quando se tem uma associação, quando se é uma coleção(muitos-um, muitos-muitos e etc), 
	  fazer a instanciação, para garantir que a coleção não comece nula e sim vazia(que é instanciada)
	  3-Não colocamos a lista no construtor porque já instanciamos ela abaixo
	  4-@ManyToMany diz que é uma relação muitos para muitos com Category*/
	
	@ManyToMany
	@JoinTable(name = "tb_product_category", 
	joinColumns = @JoinColumn(name = "tb_product_id"),
	 inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();
	
	@OneToMany(mappedBy="id.product")//Atributo que está na classe OrderItem como ID e apontando para a classe PRODUCT
	private Set<OrderItem> Items = new HashSet<OrderItem>();
	/*FAZER O GET para esse, mas para cada OrderItem, eu quero o pedido que está associado a esse produto,
	 *e não os itens do pedido que esse produto está associado. Isso não faz sentido na vida real*/
	
	public Product() {
	}

	public Product(Integer id, String name, String description, Double price, String imageUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Set<Category> getCategories(){
		return categories;
	}
	
	@JsonIgnore
	public Set<Order> getOrders(){
		//dentro dos itens de pedido(OrderItem), eu tenho os produtos, logo posso fazer o get nele
		Set<Order> setOrders = new HashSet<>();
		for(OrderItem listOrderItem : Items) {
			setOrders.add(listOrderItem.getOrder());
		}
		return setOrders;
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
