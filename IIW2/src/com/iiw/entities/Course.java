package com.iiw.entities;
import com.google.gson.annotations.SerializedName;
public class Course {
	@SerializedName("Course")
	private int rank;
	private int feesInstate;
	private int feesOutState;
	private int enrollments;
	
	public Course(){
		URI = null;
		this.name = null;
		this.rank = (Integer) null;
		this.feesInstate = (Integer) null;
		this.feesOutState = (Integer) null;
		this.enrollments = (Integer) null;
	}
	
	public Course(String uRI, String name, int rank, int feesInstate,
			int feesOutState, int enrollments) {
		super();
		URI = uRI;
		this.name = name;
		this.rank = rank;
		this.feesInstate = feesInstate;
		this.feesOutState = feesOutState;
		this.enrollments = enrollments;
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
	private String URI;
	private String name;
	

	
}
