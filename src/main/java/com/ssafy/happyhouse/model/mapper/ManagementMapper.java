package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.User2;

@Mapper
public interface ManagementMapper {
	public List<User2> selectAllUserList() ;
	public int selectCountBoardById(int id) ;
	public int deleteAllBoardById(int id) ;
	public List<Integer> selectCommentInBoardById(int id) ;
	public int deleteCommentInBoardByBoardNo(int board_no) ;

}
