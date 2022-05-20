package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.happyhouse.exception.ApartmentDealException;
import com.ssafy.happyhouse.model.dto.ApartmentDeal;

public interface ApartmentDealMapper {

	ArrayList<ApartmentDeal> dealList(int aptCode) throws ApartmentDealException, SQLException;
}
