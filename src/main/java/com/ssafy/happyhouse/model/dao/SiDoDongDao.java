package com.ssafy.happyhouse.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ssafy.happyhouse.exception.SiDoDongException;
import com.ssafy.happyhouse.model.dto.SiDo;

public class SiDoDongDao {
	private FactoryDao factory = FactoryDao.getInstance();
	private static SiDoDongDao instance = new SiDoDongDao();

	private SiDoDongDao() {
	}

	public static SiDoDongDao getInstance() {
		return instance;
	}
	
	/** 시, 도 조회  */
	public ArrayList<SiDo> selectSidoList() throws SiDoDongException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<SiDo> list = null;
		
		try {
			conn = factory.getConnection();
			stmt = conn.createStatement();
			
			String sql = "select distinct sidoName from sidocode;";
			rs = stmt.executeQuery(sql);
			
			list = new ArrayList<SiDo>();
			SiDo dto = null;
			while(rs.next()) {
				String sidoName = rs.getString("sidoName");
				
				dto = new SiDo(sidoName, "0");
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("[예외]" + e.getMessage());
		} finally {
			factory.close(conn, stmt, rs);
		}
		
		if(list == null || list.isEmpty()) {
			throw new SiDoDongException("[오류]해당 지역의 정보가 없습니다.");
		}
		
		return list;
	}
	
	/** 구, 군 조회  */
	public ArrayList<SiDo> selectGugunList(String sidoName) throws SiDoDongException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<SiDo> list = null;
		try {
			conn = factory.getConnection();
			String sql = "select distinct dongcode.gugunName from dongcode inner join guguncode on guguncode.guguncode = dongcode.gugunCode inner join sidocode on sidocode.sidocode = guguncode.sidocode where sidoname like concat('%', ?, '%')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sidoName);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<SiDo>();
			SiDo dto = null;
			while(rs.next()) {
				String gugunName = rs.getString("dongcode.gugunName");

				dto = new SiDo(gugunName, "0");
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("[예외]" + e.getMessage());
		} finally {
			factory.close(conn, pstmt, rs);
		}
		
		if(list == null || list.isEmpty()) {
			throw new SiDoDongException("[오류]해당 지역의 정보가 없습니다.");
		}
		
		return list;
	}
	
	/** 동 조회  */
	public ArrayList<SiDo> selectDongList(String gugunName) throws SiDoDongException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<SiDo> list = null;
		try {
			conn = factory.getConnection();
			String sql = "select distinct dongName from dongCode inner join guguncode on dongcode.gugunCode = guguncode.guguncode where gugunCode.gugunName like concat('%', ?, '%')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gugunName);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<SiDo>();
			SiDo dto = null;
			while(rs.next()) {
				String dongName = rs.getString("dongCode.dongName");

				dto = new SiDo(dongName, "0");
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("[예외]" + e.getMessage());
		} finally {
			factory.close(conn, pstmt, rs);
		}
		
		if(list == null || list.isEmpty()) {
			throw new SiDoDongException("[오류]해당 지역의 정보가 없습니다.");
		}
		
		return list;
	}
	
	/** 동 코드 조회 */
	public String findDongCode(String dongName) throws SiDoDongException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = factory.getConnection();
			String sql = "select dongCode from dongcode where dongName=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dongName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return (rs.getString("dongCode.dongCode"));
			}

		} catch (SQLException e) {
			System.out.println("[예외]" + e.getMessage());
		} finally {
			factory.close(conn, pstmt, rs);
		}

		throw new SiDoDongException("[오류]해당 지역의 정보가 없습니다.");
	}
}
