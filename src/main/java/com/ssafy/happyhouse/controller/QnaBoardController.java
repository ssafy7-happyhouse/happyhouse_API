package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.happyhouse.exception.ApartmentDealException;
import com.ssafy.happyhouse.model.dto.ApartmentDetail;
import com.ssafy.happyhouse.model.dto.AptSearch;
import com.ssafy.happyhouse.model.dto.Comment;
import com.ssafy.happyhouse.model.dto.QnaBoard;
import com.ssafy.happyhouse.model.dto.QnaBoard2;
import com.ssafy.happyhouse.model.dto.QnaBoardComment;
import com.ssafy.happyhouse.service.QnaBoardService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/qna")
public class QnaBoardController {

	@Autowired
	QnaBoardService qnaBoardService;

	@GetMapping()
	public ResponseEntity<?> selectAllList() {
		List<QnaBoard2> list = qnaBoardService.selectAllList();
		return new ResponseEntity<List<QnaBoard2>>(list, HttpStatus.OK);
	}

	@GetMapping("{no}")
	public ResponseEntity<?> selectBoardByNo(@PathVariable int no) {
		QnaBoard article = qnaBoardService.selectBoardByNo(no);
		return new ResponseEntity<QnaBoard>(article, HttpStatus.OK);
	}

	@GetMapping("{no}/all")
	public ResponseEntity<?> selectBoardCommentByNo(@PathVariable int no) {
		QnaBoardComment article = qnaBoardService.selectBoardCommentByNo(no);
		return new ResponseEntity<QnaBoardComment>(article, HttpStatus.OK);
	}

	@GetMapping("/{board_no}/comment")
	public ResponseEntity<?> selectCommentByNo(@PathVariable int board_no) {
		Comment comment = qnaBoardService.selectCommentByNo(board_no);
		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> insertQnaBoard(@RequestBody QnaBoard qnaBoard) {
		if (qnaBoardService.insertQnaBoard(qnaBoard.getUser_id(), qnaBoard.getTitle(), qnaBoard.getContent()) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}

	@PostMapping("/{board_no}/comment")
	public ResponseEntity<String> insertComment(@PathVariable int board_no, @RequestBody Comment comment) {
		comment.setBoard_no(board_no);
		if (qnaBoardService.insertComment(comment) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("{no}")
	public ResponseEntity<?> deleteQnaBoardbyNo(@PathVariable int no) {
		qnaBoardService.deleteCommentbyNo(no);

		if (qnaBoardService.deleteQnaBoardbyNo(no) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("{board_no}/comment")
	public ResponseEntity<?> deleteCommentbyNo(@PathVariable int board_no) {
		if (qnaBoardService.deleteCommentbyNo(board_no) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}

	@PutMapping("{no}")
	public ResponseEntity<String> updateQnaBoardbyNo(@PathVariable int no, @RequestBody QnaBoard qnaBoard) {
		System.out.println(no);
		qnaBoard.setNo(no);
		if (qnaBoardService.updateQnaBoardbyNo(qnaBoard) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{board_no}/comment")
	public ResponseEntity<String> updateCommentbyNo(@PathVariable int board_no, @RequestBody Comment comment) {
		comment.setBoard_no(board_no);
		if (qnaBoardService.updateCommentbyNo(comment) == 1) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{pageNum}/{pageSize}")
	public ResponseEntity<?> getListByPaging(@PathVariable int pageNum, @PathVariable int pageSize) {

		PageHelper.startPage(pageNum, pageSize);
//		List<QnaBoard2> list = qnaBoardService.selectAllList();

		return new ResponseEntity<PageInfo<QnaBoard2>>(PageInfo.of(qnaBoardService.selectAllList()), HttpStatus.OK);

	}

}
