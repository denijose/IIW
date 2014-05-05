package com.iiw.entities;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class University {
  
	@SerializedName("University")
	private String URI;
	private String name;
	private String type;
	private String country;
	private String city;
	private String state;
	private String acceptanceLevel;	
	private Boolean isFunded;
	private String reviews;
	
	//for edulix uiversities
	private int numOfAdmits;	
	private int numOfRejects;
	private int numOfWaiting;
	private ArrayList<Student> acceptedStudents;
	private ArrayList<Student> rejectedStudents;
	private ArrayList<Student> waitingStudents;
	

	
	public University(){
		this.URI = null;
		this.name = null;
		this.country = null;
		this.city = null;
		this.state = null;		
		this.isFunded = null;
		this.numOfAdmits = -1;
		this.numOfRejects = -1;
		this.numOfWaiting = -1;
		this.acceptedStudents = null;;
		this.rejectedStudents = null;
		this.waitingStudents = null;
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

	
	public int getNumOfAdmits() {
		return numOfAdmits;
	}

	public void setNumOfAdmits(int numOfAdmits) {
		this.numOfAdmits = numOfAdmits;
	}

	public int getNumOfRejects() {
		return numOfRejects;
	}

	public void setNumOfRejects(int numOfRejects) {
		this.numOfRejects = numOfRejects;
	}

	public int getNumOfWaiting() {
		return numOfWaiting;
	}

	public void setNumOfWaiting(int numOfWaiting) {
		this.numOfWaiting = numOfWaiting;
	}

	public ArrayList<Student> getAcceptedStudents() {
		return acceptedStudents;
	}

	public void setAcceptedStudents(ArrayList<Student> acceptedStudents) {
		this.acceptedStudents = acceptedStudents;
	}

	public ArrayList<Student> getRejectedStudents() {
		return rejectedStudents;
	}

	public void setRejectedStudents(ArrayList<Student> rejectedStudents) {
		this.rejectedStudents = rejectedStudents;
	}

	public ArrayList<Student> getWaitingStudents() {
		return waitingStudents;
	}

	public void setWaitingStudents(ArrayList<Student> waitingStudents) {
		this.waitingStudents = waitingStudents;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}
	
	public String getReviews() {
		return this.reviews;
	}
	

}
