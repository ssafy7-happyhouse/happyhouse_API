package com.ssafy.happyhouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Apartment {
	private int aptCode;
	private String aptName;
	private String dongCode;
	private String sidoName;
	private String gugunName;
	private String dongName;
	private int buildYear;
	private String jibun;
	private String lat;
	private String lng;
	private int distance;
	private int lastAmount;
	private int avgAmount;
	private String area;

}
