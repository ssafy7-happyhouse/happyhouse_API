package com.ssafy.happyhouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ApartmentDetail {
	private int aptCode;
	private String aptName;
	private String sidoName;
	private String gugunName;
	private String dongName;
	private String dongCode;
	private int buildYear;
	private String jibun;
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private String area;
	private String floor;
	private String lat;
	private String lng;
}
