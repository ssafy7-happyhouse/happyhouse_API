package com.ssafy.happyhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;

//	private UserDao dao = UserDao.getInstance();
	
	public User login(String id, String password) throws Exception {
		return userMapper.login(id, password);
	}

	public int signUp(User dto) throws Exception {
		return userMapper.insert(dto);
	}
	
	public User showInfo(String id) throws Exception {
		return userMapper.getMyInfo(id);
	}

	public int updateInfo(User dto) throws Exception {
		return userMapper.update(dto);
	}

	public int deleteInfo(String id) throws Exception {
		return userMapper.delete(id);
	}

	public String getMyPw(String id, String name, String phone) throws Exception {
		return userMapper.getMyPw(id, name, phone);
	}


}
