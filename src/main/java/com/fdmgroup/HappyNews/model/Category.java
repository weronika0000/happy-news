package com.fdmgroup.HappyNews.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
@Entity
public class Category {
	
	@Id
	@GeneratedValue
	@Column(name="category_ID")
	private Integer categoryId;
	
	private String name;
	
	@ManyToOne
	private HappyUser user;
	
	

	public Category() {
		super();
	}



	public Category(String name, HappyUser user) {
		super();
		this.name = name;
		this.user = user;
	}



	public Integer getCategoryId() {
		return categoryId;
	}


	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public HappyUser getUser() {
		return user;
	}



	public void setUser(HappyUser user) {
		this.user = user;
	}



	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + ", user=" + user + "]";
	}
	
	
	

}
