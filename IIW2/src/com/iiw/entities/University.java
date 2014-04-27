package com.iiw.entities;

public class University {
  
	private String URI;
	private String name;
	private String type;
	private String country;
	private String city;
	private String state;
	private String acceptanceLevel;
	
	private int rank;
	private int feesInstate;
	private int feesOutState;
	private int enrollments;
	
	private Boolean isFunded;
	
	public University(String URI, String name, String country, String city, String state, int rank,
			int feesInstate, int feesOutState, int enrollments , boolean isFunded){
		this.URI = URI;
		this.name = name;
		this.country = country;
		this.city = city;
		this.state = state;
		this.rank = rank;
		this.feesInstate = feesInstate;
		this.feesOutState = feesOutState;
		this.enrollments = enrollments;		
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
		this.feesInstate = feesInstate;
		this.feesOutState = feesOutState;
		this.enrollments = enrollments;
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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getFeesInstate() {
		return feesInstate;
	}

	public void setFeesInstate(int feesInstate) {
		this.feesInstate = feesInstate;
	}

	public int getFeesOutState() {
		return feesOutState;
	}

	public void setFeesOutState(int feesOutState) {
		this.feesOutState = feesOutState;
	}

	public int getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(int enrollments) {
		this.enrollments = enrollments;
	}

	public Boolean getIsFunded() {
		return isFunded;
	}

	public void setIsFunded(Boolean isFunded) {
		this.isFunded = isFunded;
	}

	

	
	
}
