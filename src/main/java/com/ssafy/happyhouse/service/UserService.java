package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.dto.User2;
import com.ssafy.happyhouse.model.dto.UserInfo;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;

//	private UserDao dao = UserDao.getInstance();
	
	public User login(String id) throws Exception {
		return userMapper.login(id);
	}

	public int signUp(User dto) throws Exception {
		return userMapper.insert(dto);
	}
	
	public User showInfo(String id) throws Exception {
		return userMapper.getMyInfo(id);
	}
	
	public String findById(String id) throws Exception {
		return userMapper.findById(id);
	}

	public int updateInfo(UserInfo userInfo) throws Exception {
		return userMapper.update(userInfo);
	}


	
	public int deleteInfo(String id) throws Exception {
		return userMapper.delete(id);
	}

	public String getMyPw(String id, String name, String phone) throws Exception {
		return userMapper.getMyPw(id, name, phone);
	}


}
