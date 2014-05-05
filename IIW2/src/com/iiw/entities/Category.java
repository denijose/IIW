package com.iiw.entities;

import com.google.gson.annotations.SerializedName;
public class Category {
	
	@SerializedName("Category")
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
