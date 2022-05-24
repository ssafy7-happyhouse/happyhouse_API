package com.ssafy.happyhouse.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.News;
import com.ssafy.happyhouse.service.NaverSearchService;

@RestController
@RequestMapping("/naver")
public class NewsController {

	@Autowired
	NaverSearchService naverSearchService;

	@GetMapping("/search")
	public ArrayList<News> searchNews() {
		return naverSearchService.searchNews();
	}
}
