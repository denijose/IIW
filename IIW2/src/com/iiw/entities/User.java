package com.iiw.entities;

public class User {

	private String userURI;
	private String userName;
	private Integer userGREQScore;
	private Integer userGREVScore;
	private Integer userGREAScore;
	private Integer userToeflScore;
	private String userCountry;
	private String userStream;
	

	public User(){	}
	
    public User(String userURI, String userName, Integer userGREQScore,
    		Integer userGREVScore, Integer userGREAScore, Integer userToeflScore, String userCountry, String userStream) {
		super();
		this.userURI = userURI;
		this.userName = userName;
		this.userGREQScore = userGREQScore;
		this.userGREVScore = userGREVScore;
		this.userGREAScore = userGREAScore;
		this.userToeflScore = userToeflScore;
		this.userCountry = userCountry;
		this.userStream = userStream;
	}

	
	public String getUserURI() {
		return userURI;
	}
	public void setUserURI(String userURI) {
		this.userURI = userURI;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserGREQScore() {
		return userGREQScore;
	}
	public void setUserGREQScore(Integer userGREQScore) {
		this.userGREQScore = userGREQScore;
	}
	public Integer getUserGREVScore() {
		return userGREVScore;
	}
	public void setUserGREVScore(Integer userGREVScore) {
		this.userGREVScore = userGREVScore;
	}
	public Integer getUserGREAScore() {
		return userGREAScore;
	}
	public void setUserGREAScore(Integer userGREAScore) {
		this.userGREAScore = userGREAScore;
	}

	public Integer getUserToeflScore() {
		return userToeflScore;
	}

	public void setUserToeflScore(Integer userToeflScore) {
		this.userToeflScore = userToeflScore;
	}
	public String getUserCountry() {
		return userCountry;
	}

	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}

	public String getUserStream() {
		return userStream;
	}

	public void setUserStream(String userStream) {
		this.userStream = userStream;
	}

	
}
