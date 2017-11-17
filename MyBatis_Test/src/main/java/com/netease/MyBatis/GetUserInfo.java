package com.netease.MyBatis;

public interface GetUserInfo {

	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(int id);
	
	public User GetUser(int id);
	
}
