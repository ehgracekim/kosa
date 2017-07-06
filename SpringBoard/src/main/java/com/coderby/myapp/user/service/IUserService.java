package com.coderby.myapp.user.service;

import com.coderby.myapp.user.model.UserVO;

public interface IUserService {
	public UserVO selectUserInfo(String id);
	public void insertUserInfo(UserVO user);
	public void updateUserInfo(UserVO user);
	public void deleteUserInfo(String id);
	public boolean checkPassword(String id, String password);
}
