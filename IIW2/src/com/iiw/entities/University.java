package com.iiw.entities;

public class University {
  
	private String URI;
	private String name;
	private String type;
	private String country;
	private String city;
	private String state;
	private String acceptanceLevel;
	
	
	
	private Boolean isFunded;
	
	public University(){
		this.URI = null;
		this.name = null;
		this.country = null;
		this.city = null;
		this.state = null;		
		this.isFunded = null;
	}
	
	public University(String URI, String name, String country, String city, String state, boolean isFunded){
		this.URI = URI;
		this.name = name;
		this.country = country;
		this.city = city;
		this.state = state;		
		this.isFunded = isFunded;
	}
	
	public University(String URI, String name, 
			String country, String city, String state,
			String type, 
			int feesInstate, int feesOutState,
			int enrollments,
			Boolean isFunded, 
			String acceptanceLevel) {
		super();
		this.URI = URI;
		this.name = name;		
		this.country = country;
		this.city = city;
		this.state = state;
		this.type = type;
		this.isFunded = isFunded;
		this.acceptanceLevel = acceptanceLevel;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAcceptanceLevel() {
		return acceptanceLevel;
	}

	public void setAcceptanceLevel(String acceptanceLevel) {
		this.acceptanceLevel = acceptanceLevel;
	}

	public Boolean getIsFunded() {
		return isFunded;
	}

	public void setIsFunded(Boolean isFunded) {
		this.isFunded = isFunded;
	}

	

	
	
}
