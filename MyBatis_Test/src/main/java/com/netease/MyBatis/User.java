package com.netease.MyBatis;

public class User {

	private int id;
	private String userName;
	private String crop;
	
	public User() {
		
	}
	
	public User(String userName, String crop) {
		this.userName = userName;
		this.crop = crop;
	}
	
	public User(int id, String userName, String crop) {
		this.id = id;
		this.userName = userName;
		this.crop = crop;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCrop() {
		return crop;
	}
	public void setCrop(String crop) {
		this.crop = crop;
	}
	
}
