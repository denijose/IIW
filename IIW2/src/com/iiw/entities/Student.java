package com.iiw.entities;

public class Student {

	private String URI;
	private String name;
	private Integer age;
	private String stream;
	private Integer greScore;
	private Integer toeflScore;
	
	public Student(){
		this.URI = null;
		this.name = null;
		this.age = null;
		this.stream = null;
		this.greScore = null;
		this.toeflScore = null;
	}
	
	public Student(String URI,String name, Integer age, String stream, Integer greScore,
			Integer toeflScore) {
		super();
		this.URI = URI;
		this.name = name;
		this.age = age;
		this.stream = stream;
		this.greScore = greScore;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public Integer getGreScore() {
		return greScore;
	}

	public void setGreScore(Integer greScore) {
		this.greScore = greScore;
	}

	public Integer getToeflScore() {
		return toeflScore;
	}

	public void setToeflScore(Integer toeflScore) {
		this.toeflScore = toeflScore;
	}

	
}
