package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.happyhouse.exception.ApartmentException;
import com.ssafy.happyhouse.model.dto.Apartment;
import com.ssafy.happyhouse.model.dto.ApartmentDetail;
import com.ssafy.happyhouse.model.dto.AptFilter;

public interface ApartmentMapper {

	ArrayList<Apartment> aptList() throws ApartmentException, SQLException;

	ArrayList<Apartment> selectList(String dongCode, String aptName) throws ApartmentException, SQLException;
	
	ArrayList<Apartment> findAllApt(AptFilter aptFilter) throws ApartmentException, SQLException;
	
	ArrayList<Apartment> findAllAptByDong(AptFilter aptFilter) throws ApartmentException, SQLException;
	
	ArrayList<Apartment> findAllAptByGugun(AptFilter aptFilter) throws ApartmentException, SQLException;

	ArrayList<Apartment> findAptByName(String aptName) throws ApartmentException, SQLException;
	
	ArrayList<ApartmentDetail> findAptByDongcode(String dongCode) throws ApartmentException, SQLException;
}
