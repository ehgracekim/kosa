package com.coderby.myapp.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.coderby.myapp.user.model.UserVO;
import com.coderby.myapp.user.service.IUserService;

public class UserMain {
	public static void main(String[] args) {
		AbstractApplicationContext context =
				new ClassPathXmlApplicationContext("application-config.xml");
		IUserService service = context.getBean("userService", IUserService.class);
		UserVO user = new UserVO();
		user.setId("hongkd");
		user.setName("홍길동");
		user.setPassword("user123");
		user.setRole("User");
		service.insertUserInfo(user); //사용자 추가
		
		System.out.println(service.selectUserInfo(user.getId()));	//사용자 정보 출력
		System.out.println(service.checkPassword("hongkd", "user124")); //false
		System.out.println(service.checkPassword("hongkd", "user123")); //true
		
		user.setPassword("user124");
		service.updateUserInfo(user);
		
	}
}
