package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.QnaBoard2;
import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.dto.User2;
import com.ssafy.happyhouse.model.dto.UserInfo;

@Mapper
public interface UserMapper {
	/** 로그인 */
	User login(String id) throws Exception;

	/** 내정보 조회 */
	User getMyInfo(String id) throws Exception;
	
	/** 내정보 조회 */
	String findById(String id) throws Exception;

	/** 비밀번호 찾기 */
	String getMyPw(String id, String name, String phone) throws Exception;

	/** 회원 등록 */
	int insert(User dto) throws Exception;

	/** 회원 탈퇴 */
	int delete(String id) throws Exception;

	/** 회원정보 변경 */
	int update(UserInfo userInfo) throws Exception;
	
	

}
