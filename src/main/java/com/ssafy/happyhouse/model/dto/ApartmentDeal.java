package com.ssafy.happyhouse.model.dto;

public class ApartmentDeal {
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private String area;
	private String floor;

	public ApartmentDeal() {
	}

	public ApartmentDeal(String dealAmount, int dealYear, int dealMonth, String area, String floor) {
		super();
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.area = area;
		this.floor = floor;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public int getDealYear() {
		return dealYear;
	}

	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}

	public int getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}



}
