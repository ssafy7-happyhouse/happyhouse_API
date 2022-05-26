package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.happyhouse.model.dto.QnaBoard2;
import com.ssafy.happyhouse.model.dto.User2;
import com.ssafy.happyhouse.service.ManagementService;
import com.ssafy.happyhouse.service.QnaBoardService;

@Controller
@RequestMapping("/management")
public class ManagementController {

	@Autowired
	ManagementService managementService;

	@GetMapping("/user/{pageNum}/{pageSize}")
	public ResponseEntity<?> selectAllUserList(@PathVariable int pageNum, @PathVariable int pageSize) {
		PageHelper.startPage(pageNum, pageSize);

//		Map<String, Object> resultMap = new HashMap();
//		HttpStatus status = null; 
//		List<User2> list = null;
//		try {
//			list = managementService.selectAllUserList();
//			resultMap.put("message", "success");
//			status = HttpStatus.ACCEPTED;
//		} catch (Exception e) {
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.ACCEPTED;
//		}
		return new ResponseEntity<PageInfo<User2>>(PageInfo.of(managementService.selectAllUserList()), HttpStatus.OK);
//		return new ResponseEntity<List<User2>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/user/{userid}")
	public ResponseEntity<?> selectCountBoardById(@PathVariable int userid) {
		
		Map<String, Object> resultMap = new HashMap();
		HttpStatus status = null; 
//		List<User2> list = null;
//		try {
			int result = managementService.selectCountBoardById(userid);
			if(result != 0) {
			resultMap.put("message", "success");
			status = HttpStatus.ACCEPTED;
				
			}else {
				resultMap.put("message", "fail");
				status = HttpStatus.ACCEPTED;
			}
//		} catch (Exception e) {
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.ACCEPTED;
//		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//		return new ResponseEntity<List<User2>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{userid}")
	public ResponseEntity<?> deleteAllBoardById(@PathVariable int userid) {

		Map<String, Object> resultMap = new HashMap();
		HttpStatus status = null; 
//		List<User2> list = null;
//		try {
			// 댓글이 있는지
		List<Integer> list = managementService.selectCommentInBoardById(userid);
		if(list.size() != 0) {
			System.out.println("댓글 개수는 :" + list.size());
			// 있으면 댓글 먼저 삭제
			
			for (int i = 0; i < list.size(); i++) {
				int result1 = managementService.deleteCommentInBoardByBoardNo(list.get(i));
				System.out.println("댓글 삭제 :" + list.get(i));
				
			}
			
			int result2 = managementService.deleteAllBoardById(userid);
			if(result2 != 0) {
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			}else {
				resultMap.put("message", "fail");
				status = HttpStatus.ACCEPTED;
			} 
			
		}else{
			int result2 = managementService.deleteAllBoardById(userid);
			if(result2 != 0) {
				resultMap.put("message", "success");
				status = HttpStatus.ACCEPTED;
			}else {
				resultMap.put("message", "fail");
				status = HttpStatus.ACCEPTED;
			} 
			
		}
		
		
		
//		} catch (Exception e) {
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.ACCEPTED;
//		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
