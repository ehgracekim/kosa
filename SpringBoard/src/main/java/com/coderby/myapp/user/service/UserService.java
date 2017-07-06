package com.coderby.myapp.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderby.myapp.user.dao.IUserRepository;
import com.coderby.myapp.user.model.UserVO;

@Service//빈으로 등록되게끔 아노테이션 이용해서 빈 설정
public class UserService implements IUserService {

	@Autowired
	IUserRepository userRepository;//의존성 설정 
	
	@Override
	public UserVO selectUserInfo(String id) {
		return userRepository.selectUserInfo(id);
	}

	@Override
	public void insertUserInfo(UserVO user) {
		userRepository.insertUserInfo(user);

	}

	@Override
	public void updateUserInfo(UserVO user) {
		userRepository.updateUserInfo(user);

	}

	@Override
	public void deleteUserInfo(String id) {
		userRepository.deleteUserInfo(id);

	}

	@Override
	public boolean checkPassword(String id, String password){
		String dbpw = userRepository.getPassword(id);
		if(dbpw != null && dbpw.equals(password)) {
			return true;			
		}else {
		return false;
	}
}
}