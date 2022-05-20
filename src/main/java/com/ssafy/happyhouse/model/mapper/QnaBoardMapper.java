package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.Comment;
import com.ssafy.happyhouse.model.dto.QnaBoard;
import com.ssafy.happyhouse.model.dto.QnaBoard2;
import com.ssafy.happyhouse.model.dto.QnaBoardComment;

@Mapper
public interface QnaBoardMapper {
	public List<QnaBoard2> selectAllList();
	public QnaBoard selectBoardByNo(int no);
	public Comment selectCommentByNo(int board_no);
	public QnaBoardComment selectBoardCommentByNo(int no);
	
	public int updateBoard(QnaBoard board);
	public int deleteBoard(int articleno);

	public int insertQnaBoard(String user_id, String title, String content);
	public int insertComment(Comment comment);
	
	public int deleteQnaBoardbyNo(int no);
	public int deleteCommentbyNo(int board_no);
	
	public int updateQnaBoardbyNo(QnaBoard qnaBoard);
	public int updateCommentbyNo(Comment comment);
	
	
}
