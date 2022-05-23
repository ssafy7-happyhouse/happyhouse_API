package com.ssafy.happyhouse.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.exception.ApartmentException;
import com.ssafy.happyhouse.model.dto.Apartment;
import com.ssafy.happyhouse.model.dto.ApartmentDetail;
import com.ssafy.happyhouse.model.dto.AptFilter;
import com.ssafy.happyhouse.model.mapper.ApartmentMapper;

@Service
public class ApartmentService {

	@Autowired
	private ApartmentMapper apartmentMapper;

	public ArrayList<Apartment> aptList(String lat, String lng, double km) throws ApartmentException, SQLException {
		ArrayList<Apartment> list = apartmentMapper.aptList();
		if (list.size() == 0) {
			throw new ApartmentException();
		}

		if (lat != null && lng != null) {
			aptSort(list, lat, lng);
		}

		if (km != 0) {
			int index = distanceFilter(list, km);
			if (index == -1) {
				throw new ApartmentException();
			}
			return new ArrayList<>(list.subList(0, index));
		}

		return list;
	}

	public ArrayList<Apartment> selectList(String dongCode, String aptName, String lat, String lng, double km)
			throws ApartmentException, SQLException {
		aptName = aptName == null ? "" : aptName.trim();

		ArrayList<Apartment> list = apartmentMapper.selectList(dongCode, aptName);
		if (list.size() == 0) {
			throw new ApartmentException();
		}

		if (lat != null && lng != null) {
			aptSort(list, lat, lng);
		}

		if (km != 0) {
			int index = distanceFilter(list, km);
			if (index == -1) {
				throw new ApartmentException();
			}
			return new ArrayList<>(list.subList(0, index));
		}

		return list;
	}

	public ArrayList<Apartment> findAllApt(AptFilter aptFilter) throws ApartmentException, SQLException {
		return apartmentMapper.findAllApt(aptFilter);
	}
	
	public ArrayList<ApartmentDetail> findAptByDongcode(String dongCode) throws ApartmentException, SQLException {
		return apartmentMapper.findAptByDongcode(dongCode);
	}

	public ArrayList<Apartment> findAptByName(String aptName) throws ApartmentException, SQLException {
		return apartmentMapper.findAptByName(aptName);
	}
	
	private void aptSort(ArrayList<Apartment> list, String lat, String lng) {
		double latDouble = Double.parseDouble(lat);
		double lngDouble = Double.parseDouble(lng);

		for (Apartment a : list) {
			a.setDistance(distance(Double.parseDouble(a.getLat()), Double.parseDouble(a.getLng()), latDouble, lngDouble,
					"kilometer"));
		}

		Collections.sort(list, (o1, o2) -> {
			if (o1.getDistance() > o2.getDistance()) {
				return 1;
			} else {
				return -1;
			}
		});
	}

	private int distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;

		if (unit == "kilometer") {
			dist = dist * 1.6093044;
		} else if (unit == "meter") {
			dist = dist * 16093.044;
		}

		return (int) Math.floor(dist);
	}

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	private int distanceFilter(ArrayList<Apartment> list, double km) {

		int left = 0;
		int right = list.size() - 1;

		while (left <= right) {
			int mid = (left + right) / 2;
			if (list.get(mid).getDistance() >= km) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return right + 1;
	}
}
