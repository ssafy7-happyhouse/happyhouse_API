package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.happyhouse.exception.SiDoDongException;
import com.ssafy.happyhouse.model.dto.Dong;
import com.ssafy.happyhouse.model.dto.Gugun;
import com.ssafy.happyhouse.model.dto.SiDo;
import com.ssafy.happyhouse.service.SiDoDongService;

@Controller
public class addressController {

	@Autowired
	SiDoDongService siDoDongService;

	@GetMapping("/")
	public String index(Model model) {

		try {
			List<SiDo> sidoList = siDoDongService.selectSidoList();

			model.addAttribute("sidoList", sidoList);
		} catch (SiDoDongException | SQLException e) {
			e.printStackTrace();
		}

		return "index";
	}

	@ResponseBody
	@GetMapping("/gugun")
	public List<Gugun> gugun(String sidoCode) {
		List<Gugun> sidoList = null;
		try {
			sidoList = siDoDongService.selectGugunList(sidoCode);
		} catch (SiDoDongException | SQLException e) {
			e.printStackTrace();
		}

		return sidoList;
	}
	
	
	@ResponseBody
	@GetMapping("/dong")
	public List<Dong> dong(String gugunCode) {
		List<Dong> dongList = null;
		try {
			dongList = siDoDongService.selectDongList(gugunCode);
		} catch (SiDoDongException | SQLException e) {
			e.printStackTrace();
		}

		return dongList;
	}
	
	@ResponseBody
	@GetMapping("/dong/{dongName}")
	public ResponseEntity<?> search(@PathVariable String dongName) {
		try {
			return new ResponseEntity<List<Dong>>(siDoDongService.searchDongList(dongName), HttpStatus.OK);
		} catch (SiDoDongException | SQLException e) {
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
	}
}

