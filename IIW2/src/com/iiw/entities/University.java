package com.iiw.entities;

public class University {
  
	private String URI;


	private String name;
	private String type;
	private String country;
	private Integer fees;
	private Boolean isFunded;
	private String acceptanceLevel;
	
	public University(String URI, String name, String type, String country, Integer fees,
			Boolean isFunded, String acceptanceLevel) {
		super();
		this.URI = URI;
		this.name = name;
		this.type = type;
		this.country = country;
		this.fees = fees;
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

	public Integer getFees() {
		return fees;
	}

	public void setFees(Integer fees) {
		this.fees = fees;
	}

	public Boolean getIsFunded() {
		return isFunded;
	}

	public void setIsFunded(Boolean isFunded) {
		this.isFunded = isFunded;
	}

	public String getAcceptanceLevel() {
		return acceptanceLevel;
	}

	public void setAcceptanceLevel(String acceptanceLevel) {
		this.acceptanceLevel = acceptanceLevel;
	}

	
	
}
