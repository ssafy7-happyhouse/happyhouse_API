package com.ssafy.happyhouse.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ApartmentDeal {
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private String area;
	private String floor;
}
