package com.iiw.entities;

public class Category {
	
	private  String URI;
	private  String name;
	
	public Category(){}
	
	public Category(String uRI, String name) {
		super();
		URI = uRI;
		this.name = name;
	}
	public String getURI() {
		return URI;
	}
	public void setURI(String uRI) {
		URI = uRI;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
