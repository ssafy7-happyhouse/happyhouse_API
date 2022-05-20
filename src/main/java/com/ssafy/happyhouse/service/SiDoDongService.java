package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.exception.SiDoDongException;
import com.ssafy.happyhouse.model.dto.Dong;
import com.ssafy.happyhouse.model.dto.Gugun;
import com.ssafy.happyhouse.model.dto.SiDo;
import com.ssafy.happyhouse.model.mapper.SiDoDongMapper;

@Service
public class SiDoDongService {

	@Autowired
	private SiDoDongMapper siDoDongMapper;

	public ArrayList<SiDo> selectSidoList() throws SiDoDongException, SQLException {
		return siDoDongMapper.selectSidoList();
	}

	public ArrayList<Gugun> selectGugunList(String sidoCode) throws SiDoDongException, SQLException {
		ArrayList<Gugun> sidoList = siDoDongMapper.selectGugunList(sidoCode);

		return (ArrayList<Gugun>) sidoList.stream()
				.filter(gu -> gu.getGugunName().split(" ").length > 1)
				.distinct()
				.collect(Collectors.toList());
	}

	public ArrayList<Dong> selectDongList(String gugunCode) throws SiDoDongException, SQLException {
		
		ArrayList<Dong> dongList = siDoDongMapper.selectDongList(gugunCode);

		return (ArrayList<Dong>) dongList.stream()
				.filter(dong -> dong.getDongName()!= null && dong.getDongName().length()>0)
				.distinct()
				.collect(Collectors.toList());
		
	}
	
	public ArrayList<Dong> searchDongList(String dongName) throws SiDoDongException, SQLException {
		return siDoDongMapper.searchDongList(dongName);
	}
}
