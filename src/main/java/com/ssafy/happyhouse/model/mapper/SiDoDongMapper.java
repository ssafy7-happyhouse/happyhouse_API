package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.happyhouse.exception.ApartmentException;
import com.ssafy.happyhouse.exception.SiDoDongException;
import com.ssafy.happyhouse.model.dto.Apartment;
import com.ssafy.happyhouse.model.dto.Dong;
import com.ssafy.happyhouse.model.dto.Gugun;
import com.ssafy.happyhouse.model.dto.SiDo;

public interface SiDoDongMapper {

	ArrayList<SiDo> selectSidoList() throws SiDoDongException, SQLException;

	ArrayList<Gugun> selectGugunList(String sidoCode) throws SiDoDongException, SQLException;

	ArrayList<Dong> selectDongList(String gugunCode) throws SiDoDongException, SQLException;

	ArrayList<Dong> searchDongList(String dongName) throws SiDoDongException, SQLException;
}
