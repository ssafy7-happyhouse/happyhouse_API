package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.Comment;
import com.ssafy.happyhouse.model.dto.QnaBoard;
import com.ssafy.happyhouse.model.dto.QnaBoard2;
import com.ssafy.happyhouse.model.dto.QnaBoardComment;
import com.ssafy.happyhouse.model.mapper.QnaBoardMapper;

@Service
public class QnaBoardService {

	@Autowired
	private QnaBoardMapper qnaBoardMapper;

	public List<QnaBoard2> selectAllList() {
		return qnaBoardMapper.selectAllList();
	}

	public QnaBoard selectBoardByNo(int no) {
		return qnaBoardMapper.selectBoardByNo(no);
	}
	
	public QnaBoardComment selectBoardCommentByNo(int no) {
		return qnaBoardMapper.selectBoardCommentByNo(no);
	}

	public Comment selectCommentByNo(int board_no) {
		return qnaBoardMapper.selectCommentByNo(board_no);
	}

	public int insertQnaBoard(String user_id, String title, String content) {
		return qnaBoardMapper.insertQnaBoard(user_id, title, content);
	}

	public int insertComment(Comment comment) {
		return qnaBoardMapper.insertComment(comment);
	}
	
	public int updateQnaBoardbyNo(QnaBoard qnaBoard) {
		return qnaBoardMapper.updateQnaBoardbyNo( qnaBoard);
	}
	
	public int updateCommentbyNo(Comment comment) {
		return qnaBoardMapper.updateCommentbyNo( comment);
	}
	
	public int deleteQnaBoardbyNo(int no) {
		return qnaBoardMapper.deleteQnaBoardbyNo(no);
	}

	public int deleteCommentbyNo(int board_no) {
		return qnaBoardMapper.deleteCommentbyNo(board_no);
	}

}
