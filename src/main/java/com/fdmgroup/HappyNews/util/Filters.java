package com.fdmgroup.HappyNews.util;

public class Filters {

	private String category;
	private String location;
	private String author;
	
	
	
	public Filters() {
		this.category = "No filter";
		this.location = "No filter";
		this.author = "No filter";
	}
	
	public Filters(String category, String location) {
		this.category = category;
		this.location = location;
	}
	
	public Filters(String category, String location, String author) {
		
		this.category = category;
		this.location = location;
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Filters [category=" + category + ", location=" + location + ", author=" + author + "]";
	}
	
	
}


	