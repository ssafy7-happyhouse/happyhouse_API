package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ssafy.happyhouse.exception.ApartmentException;
import com.ssafy.happyhouse.model.dto.Apartment;

public class ApartmentDao {
	private FactoryDao factory = FactoryDao.getInstance();
	private static ApartmentDao instance = new ApartmentDao();

	private ApartmentDao() {
	}

	public static ApartmentDao getInstance() {
		return instance;
	}

	/** 아파트 전체 조회 */
	public ArrayList<Apartment> aptList() throws ApartmentException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Apartment> list = null;
		try {
			conn = factory.getConnection();
			String sql = "select * from houseinfo";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			list = new ArrayList<Apartment>();
			Apartment dto = null;
			while (rs.next()) {
				int aptCode = rs.getInt("aptCode");
				String dongCode = rs.getString("dongCode");
				String dongName = rs.getString("dongName");
				String aptName = rs.getString("aptName");
				int buildYear = rs.getInt("buildYear");
				String jibun = rs.getString("jibun");
				String lat = rs.getString("lat");
				String lng = rs.getString("lng");

				dto = new Apartment(aptCode, aptName, dongCode, dongName, buildYear, jibun, lat, lng);
				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("[예외]" + e.getMessage());
		} finally {
			factory.close(conn, pstmt, rs);
		}

		if (list == null || list.isEmpty()) {
			throw new ApartmentException("[오류]해당 지역의 정보가 없습니다.");
		}

		return list;
	}

//	/** 해당 아파트 조회 */
//	public ArrayList<Apartment> selectListByName(String findAptName) throws ApartmentException {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		ArrayList<Apartment> list = null;
//
//		try {
//			conn = factory.getConnection();
//
//			String sql = "select * from houseinfo where aptName like concat('%', ?, '%')";
//
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setString(1, findAptName);
//
//			rs = pstmt.executeQuery();
//
//			list = new ArrayList<Apartment>();
//			Apartment dto = null;
//			while (rs.next()) {
//
//				int aptCode = rs.getInt("aptCode");
//				String aptName = rs.getString("aptName");
//				String dongCode = rs.getString("dongCode");
//				String dongName = rs.getString("dongName");
//				int buildYear = rs.getInt("buildYear");
//				String jibun = rs.getString("jibun");
//				String lat = rs.getString("lat");
//				String lng = rs.getString("lng");
//
//				dto = new Apartment(aptCode, aptName, dongCode, dongName, buildYear, jibun, lat, lng);
//				list.add(dto);
//			}
//		} catch (SQLException e) {
//			System.out.println("[예외]" + e.getMessage());
//		} finally {
//			factory.close(conn, pstmt, rs);
//		}
//
//		if (list == null || list.isEmpty()) {
//			throw new ApartmentException("[오류]등록된 아파트의 정보가 없습니다.");
//		}
//
//		return list;
//	}

	/** 동별 아파트 조회 */
	public ArrayList<Apartment> selectList(String dongCode, String aptName) throws ApartmentException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		ArrayList<Apartment> list = null;
		try {
			conn = factory.getConnection();
			String sql = "select * from houseinfo where dongCode=? ";
			if (!aptName.isEmpty()) {
				sql += " and aptName like concat('%', ?, '%') ";
			}

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dongCode);
			if (!aptName.isEmpty()) {
				pstmt.setString(2, aptName);
			}

			rs = pstmt.executeQuery();

			list = new ArrayList<Apartment>();
			Apartment dto = null;
			while (rs.next()) {
				int aptCode = rs.getInt("aptCode");
				String dongName = rs.getString("dongName");
				aptName = rs.getString("aptName");
				int buildYear = rs.getInt("buildYear");
				String jibun = rs.getString("jibun");
				String lat = rs.getString("lat");
				String lng = rs.getString("lng");

				dto = new Apartment(aptCode, aptName, dongCode, dongName, buildYear, jibun, lat, lng);
				list.add(dto);
			}

		} catch (SQLException e) {
			System.out.println("[예외]" + e.getMessage());
		} finally {
			factory.close(conn, pstmt, rs);
		}

		if (list == null || list.isEmpty()) {
			throw new ApartmentException("[오류]해당 지역의 정보가 없습니다.");
		}

		return list;
	}

}
