package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.happyhouse.exception.ApartmentDealException;
import com.ssafy.happyhouse.exception.ApartmentException;
import com.ssafy.happyhouse.model.dto.Apartment;
import com.ssafy.happyhouse.model.dto.ApartmentDeal;
import com.ssafy.happyhouse.service.ApartmentDealService;
import com.ssafy.happyhouse.service.ApartmentService;

@Controller
@RequestMapping("/apartment")
public class ApartmentController {

	@Autowired
	ApartmentService apartmentService;

	@Autowired
	ApartmentDealService apartmentDealService;

	@GetMapping("/aptList")
	public String aptList(Model model, @Nullable String sort, @Nullable String lat, @Nullable String lng,
			@Nullable Double km) {
		try {
			ArrayList<Apartment> list = apartmentService.aptList(lat, lng, km == null ? 0 : km);
			model.addAttribute("list", list);
			return "/search/search";
		} catch (ApartmentException e) {
			model.addAttribute("message", "[에러] 검색 실패");
			model.addAttribute("messageDetail", "검색된 아파트 내역이 없습니다.");
			return "error";
		} catch (SQLException e) {
			return "error";
		}
	}

	@GetMapping("/selectList")
	public String selectList(@Nullable String aptName, String dong, Model model, @Nullable String sort,
			@Nullable String lat, @Nullable String lng, @Nullable Double km) {

		try {
			ArrayList<Apartment> list = apartmentService.selectList(dong, aptName, lat, lng, km == null ? 0 : km);
			model.addAttribute("list", list);
			return "/search/search";
		} catch (ApartmentException e) {
			model.addAttribute("message", "[에러] 검색 실패");
			model.addAttribute("messageDetail", "검색된 아파트 내역이 없습니다.");
			return "error";
		} catch (SQLException e) {
			model.addAttribute("message", "[에러] 서버 에러");
			model.addAttribute("messageDetail", "서버내에 에러가 발생했습니다.");
			return "error";
		}
	}

	@GetMapping("/deal")
	public String deal(String aptCode, String lat, String lng, Model model) {
		int aptCodeNum = Integer.parseInt(aptCode);

		try {
			ArrayList<ApartmentDeal> list = apartmentDealService.dealList(aptCodeNum);
			model.addAttribute("list", list);
			model.addAttribute("lng", lng);
			model.addAttribute("lat", lat);

			return "/search/detail";
		} catch (ApartmentDealException e) {
			model.addAttribute("message", "[에러] 검색 실패");
			model.addAttribute("messageDetail", "검색된 거래내역이 없습니다.");
			return "error";
		} catch (SQLException e) {
			model.addAttribute("message", "[에러] 서버 에러");
			model.addAttribute("messageDetail", "서버내에 에러가 발생했습니다.");
			return "error";
		}
	}

	@ResponseBody
	@GetMapping
	public ResponseEntity<?> findApartmentAll(){
		try {
			return new ResponseEntity<List<Apartment>>(apartmentService.findAllApt(), HttpStatus.OK);
		} catch (ApartmentException |SQLException e) {
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);	
		} 
	}
	
	@ResponseBody
	@GetMapping("/{aptName}")
	public ResponseEntity<?> findApartmentByName(@PathVariable String aptName){
		try {
			return new ResponseEntity<List<Apartment>>(apartmentService.findAptByName(aptName), HttpStatus.OK);
		} catch (ApartmentException |SQLException e) {
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);	
		} 
	}
	
}
