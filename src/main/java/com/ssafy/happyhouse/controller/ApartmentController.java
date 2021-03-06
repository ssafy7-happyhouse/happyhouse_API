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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssafy.happyhouse.exception.ApartmentDealException;
import com.ssafy.happyhouse.exception.ApartmentException;
import com.ssafy.happyhouse.model.dto.Apartment;
import com.ssafy.happyhouse.model.dto.ApartmentDeal;
import com.ssafy.happyhouse.model.dto.ApartmentDetail;
import com.ssafy.happyhouse.model.dto.AptFilter;
import com.ssafy.happyhouse.model.dto.AptSearch;
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
	@GetMapping("/deal/{aptCode}")
	public ResponseEntity<?> findAptDetail(@PathVariable String aptCode, String pageNum, String pageSize) {
		int aptCodeNum = Integer.parseInt(aptCode);
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));

		try {
			return new ResponseEntity<PageInfo<ApartmentDetail>>(
					PageInfo.of(apartmentDealService.findAptDetail(aptCodeNum)), HttpStatus.OK);
		} catch (ApartmentDealException | SQLException e) {
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
	}

	@ResponseBody
	@GetMapping
	public ResponseEntity<?> findApartmentAll(String minAmount, String maxAmount, String minArea, String maxArea,
			String minBuildYear, String maxBuildYear) {
		try {
			return new ResponseEntity<List<Apartment>>(apartmentService.findAllApt(AptFilter.builder()
					.minDealAmount(Integer.parseInt(minAmount)).maxDealAmount(Integer.parseInt(maxAmount))
					.maxArea(Integer.parseInt(maxArea)).minArea(Integer.parseInt(minArea))
					.maxBuildYear(Integer.parseInt(maxBuildYear)).minBuildYear(Integer.parseInt(minBuildYear)).build()),
					HttpStatus.OK);
		} catch (ApartmentException | SQLException e) {
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
	}
	
	@ResponseBody
	@GetMapping("/dong")
	public ResponseEntity<?> findAllAptByDong(String minAmount, String maxAmount, String minArea, String maxArea,
			String minBuildYear, String maxBuildYear) {
		try {
			return new ResponseEntity<List<Apartment>>(apartmentService.findAllAptByDong(AptFilter.builder()
					.minDealAmount(Integer.parseInt(minAmount)).maxDealAmount(Integer.parseInt(maxAmount))
					.maxArea(Integer.parseInt(maxArea)).minArea(Integer.parseInt(minArea))
					.maxBuildYear(Integer.parseInt(maxBuildYear)).minBuildYear(Integer.parseInt(minBuildYear)).build()),
					HttpStatus.OK);
		} catch (ApartmentException | SQLException e) {
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
	}
	
	@ResponseBody
	@GetMapping("/gugun")
	public ResponseEntity<?> findAllAptByGugun(String minAmount, String maxAmount, String minArea, String maxArea,
			String minBuildYear, String maxBuildYear) {
		try {
			return new ResponseEntity<List<Apartment>>(apartmentService.findAllAptByGugun(AptFilter.builder()
					.minDealAmount(Integer.parseInt(minAmount)).maxDealAmount(Integer.parseInt(maxAmount))
					.maxArea(Integer.parseInt(maxArea)).minArea(Integer.parseInt(minArea))
					.maxBuildYear(Integer.parseInt(maxBuildYear)).minBuildYear(Integer.parseInt(minBuildYear)).build()),
					HttpStatus.OK);
		} catch (ApartmentException | SQLException e) {
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
	}

	@ResponseBody
	@GetMapping("/{aptName}")
	public ResponseEntity<?> findApartmentByName(@PathVariable String aptName) {
		try {
			return new ResponseEntity<List<Apartment>>(apartmentService.findAptByName(aptName), HttpStatus.OK);
		} catch (ApartmentException | SQLException e) {
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
	}

	@ResponseBody
	@GetMapping("/dong/{dongCode}")
	public ResponseEntity<?> findAptByDongcode(@PathVariable String dongCode, String pageNum, String pageSize) {
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));

		try {
			return new ResponseEntity<PageInfo<ApartmentDetail>>(
					PageInfo.of(apartmentService.findAptByDongcode(dongCode)), HttpStatus.OK);
		} catch (ApartmentException | SQLException e) {
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
	}

	@ResponseBody
	@GetMapping("/dong/{dongCode}/name/{aptName}")
	public ResponseEntity<?> findAptDetailByName(@PathVariable String aptName, @PathVariable String dongCode,
			String pageNum, String pageSize) {
		PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));

		try {
			return new ResponseEntity<PageInfo<ApartmentDetail>>(
					PageInfo.of(apartmentDealService
							.findAptDetailByName(AptSearch.builder().aptName(aptName).dongCode(dongCode).build())),
					HttpStatus.OK);
		} catch (ApartmentDealException | SQLException e) {
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
	}

	@ResponseBody
	@GetMapping("/deal/chart/{aptCode}")
	public ResponseEntity<?> findAptAmountByAptCode(@PathVariable int aptCode) {

		try {
			return new ResponseEntity<List<ApartmentDeal>>(apartmentDealService.findAptAmountByAptCode(aptCode),
					HttpStatus.OK);
		} catch (ApartmentDealException | SQLException e) {
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
	}
}
