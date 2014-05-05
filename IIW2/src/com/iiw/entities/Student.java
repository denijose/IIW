package com.iiw.entities;

public class Student {

	private String URI;
	private String name;
	private Double undergradScore;
	private String details;
	private Integer greQScore;
	private Integer toeflScore;
	
	public Student(){
		this.URI = null;
		this.name = null;
		this.undergradScore = null;
		this.details = null;
		this.greQScore = null;
		this.toeflScore = null;
	}
	
	public Student(String URI,String name, Double undergradScore, String details, Integer greQScore,
			Integer toeflScore) {
		super();
		this.URI = URI;
		this.name = name;
		this.undergradScore = undergradScore;
		this.details = details;
		this.greQScore = greQScore;
		this.toeflScore = toeflScore;
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

	public Double getUndergradScore() {
		return undergradScore;
	}

	public void setUndergradScore(Double undergradScore) {
		this.undergradScore = undergradScore;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getGreQScore() {
		return greQScore;
	}

	public void setGreQScore(Integer greQScore) {
		this.greQScore = greQScore;
	}

	public Integer getToeflScore() {
		return toeflScore;
	}

	public void setToeflScore(Integer toeflScore) {
		this.toeflScore = toeflScore;
	}

	
}
