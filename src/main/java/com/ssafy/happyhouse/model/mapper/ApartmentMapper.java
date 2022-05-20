package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.happyhouse.exception.ApartmentException;
import com.ssafy.happyhouse.model.dto.Apartment;

public interface ApartmentMapper {

	ArrayList<Apartment> aptList() throws ApartmentException, SQLException;

	ArrayList<Apartment> selectList(String dongCode, String aptName) throws ApartmentException, SQLException;
}
