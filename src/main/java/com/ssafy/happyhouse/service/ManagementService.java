package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.User2;
import com.ssafy.happyhouse.model.mapper.ManagementMapper;

@Service
public class ManagementService {
		@Autowired
		private ManagementMapper managementMapper;
		
		public List<User2> selectAllUserList(){
			return managementMapper.selectAllUserList();
		}
}
