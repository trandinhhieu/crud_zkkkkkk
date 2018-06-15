package com.hieu.zzzzzzzzzzzzz.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private String image;
	@Column(nullable = false)
	private int price;
	@Column(nullable = false)
	private int quantity;
	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;
	
	public Product() {
	}

	public Product(Integer id, String name, String description, String image, int price, int quantity,
			Category category) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", image=" + image + ", price="
				+ price + ", quantity=" + quantity + ", category=" + category + "]";
	}
	
//	@Command
//	@NotifyChange("*")
//	public void editProduct(@BindingParam("close") Window wd) {
//		dao.save(this);
//		wd.detach();
//	}

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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getUrl() {
		return "/upload/" + this.getImage();
	}
}
