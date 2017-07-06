package com.coderby.myapp.user.dao;

import com.coderby.myapp.user.model.UserVO;

public interface IUserRepository {
	public UserVO selectUserInfo(String id);
	public void insertUserInfo(UserVO user);
	public void updateUserInfo(UserVO user);
	public void deleteUserInfo(String id);
	public String getPassword(String id);
}
