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
public class AptFilter {
	private int minDealAmount;
	private int maxDealAmount;
	private int minArea;
	private int maxArea;
	private int minBuildYear;
	private int maxBuildYear;
}
