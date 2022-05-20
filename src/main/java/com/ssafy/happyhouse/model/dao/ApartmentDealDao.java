package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ssafy.happyhouse.exception.ApartmentDealException;
import com.ssafy.happyhouse.model.dto.ApartmentDeal;

public class ApartmentDealDao {
	private FactoryDao factory = FactoryDao.getInstance();
	private static ApartmentDealDao instance = new ApartmentDealDao();

	private ApartmentDealDao() {
	}

	public static ApartmentDealDao getInstance() {
		return instance;
	}

	/** 해당 아파트 거래 전체 조회 */
	public ArrayList<ApartmentDeal> dealList(int aptCode) throws ApartmentDealException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<ApartmentDeal> list = null;
		try {
			conn = factory.getConnection();
			String sql = "select * from housedeal where aptCode=? order by dealYear desc";
			
			pstmt = conn.prepareStatement(sql);
				
			pstmt.setInt(1, aptCode);
			rs = pstmt.executeQuery();

			list = new ArrayList<ApartmentDeal>();
			ApartmentDeal dto = null;
			while (rs.next()) {
				String dealAmount = rs.getString("dealAmount");
				int dealYear = rs.getInt("dealYear");
				int dealMonth = rs.getInt("dealMonth");

				String area = rs.getString("area");
				String floor = rs.getString("floor");

				dto = new ApartmentDeal(dealAmount, dealYear, dealMonth, area, floor);
				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("[예외]" + e.getMessage());
		} finally {
			factory.close(conn, pstmt, rs);
		}

		if (list == null || list.isEmpty()) {
			throw new ApartmentDealException("[오류]해당 아파트의 거래 정보가 없습니다.");
		}
		
		return list;
	}
}
