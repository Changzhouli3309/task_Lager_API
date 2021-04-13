package lagerApi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


@Entity(name = "product")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String product_id;
	
	@Column(nullable = false, length = 20)
	private String name;
	
	@Column(nullable = false)
	private int cost;
	
	@Column(nullable = false, length = 20)
	private String category;

	public Product() {
	}

	public Product(String name, int cost, String category) {
		this.name = name;
		this.cost = cost;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getId() {
		return serialVersionUID;
	}

	public String getProduct_id() {
		return product_id;
	}
	
}
