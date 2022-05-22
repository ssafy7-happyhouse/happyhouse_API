package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.happyhouse.exception.ApartmentDealException;
import com.ssafy.happyhouse.model.dto.ApartmentDeal;
import com.ssafy.happyhouse.model.dto.ApartmentDetail;
import com.ssafy.happyhouse.model.dto.AptSearch;

public interface ApartmentDealMapper {

	ArrayList<ApartmentDeal> dealList(int aptCode) throws ApartmentDealException, SQLException;

	ArrayList<ApartmentDetail> findAptDetail(int aptCode) throws ApartmentDealException, SQLException;

	ArrayList<ApartmentDetail> findAptDetailByName(AptSearch aptSearch) throws ApartmentDealException, SQLException;
}
