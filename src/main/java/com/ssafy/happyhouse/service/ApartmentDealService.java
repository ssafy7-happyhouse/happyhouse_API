package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.exception.ApartmentDealException;
import com.ssafy.happyhouse.exception.ApartmentException;
import com.ssafy.happyhouse.model.dao.ApartmentDealDao;
import com.ssafy.happyhouse.model.dto.ApartmentDeal;
import com.ssafy.happyhouse.model.dto.ApartmentDetail;
import com.ssafy.happyhouse.model.dto.AptSearch;
import com.ssafy.happyhouse.model.mapper.ApartmentDealMapper;

@Service
public class ApartmentDealService {

	@Autowired
	private ApartmentDealMapper apartmentDealMapper;

	public ArrayList<ApartmentDeal> dealList(int aptCode) throws ApartmentDealException, SQLException {
		ArrayList<ApartmentDeal> list = apartmentDealMapper.dealList(aptCode);
		if (list.size() == 0) {
			throw new ApartmentDealException();
		}
		return list;
	}
	
	public ArrayList<ApartmentDetail> findAptDetail(int aptCode) throws ApartmentDealException, SQLException {
		ArrayList<ApartmentDetail> list = apartmentDealMapper.findAptDetail(aptCode);
		if (list.size() == 0) {
			throw new ApartmentDealException();
		}
		return list;
	}
	
	public ArrayList<ApartmentDetail> findAptDetailByName(AptSearch aptSearch) throws ApartmentDealException, SQLException {
		ArrayList<ApartmentDetail> list = apartmentDealMapper.findAptDetailByName(aptSearch);
		if (list.size() == 0) {
			throw new ApartmentDealException();
		}
		return list;
	}
}
