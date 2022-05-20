package com.ssafy.happyhouse.model.dto;

public class Apartment {
	private int aptCode;
	private String aptName;
	private String dongCode;
	private String dongName;
	private int buildYear;
	private String jibun;
	private String lat;
	private String lng;
	private int distance;
	
	/**
	 * @param aptCode
	 * @param aptName
	 * @param dongCode
	 * @param dongName
	 * @param buildYear
	 * @param jibun
	 * @param lat
	 * @param lng
	 * @param img
	 * @param distance
	 */
	
	public Apartment() {}

	public Apartment(int aptCode, String aptName, String dongCode, String dongName, int buildYear, String jibun,
			String lat, String lng, int distance) {
		super();
		this.aptCode = aptCode;
		this.aptName = aptName;
		this.dongCode = dongCode;
		this.dongName = dongName;
		this.buildYear = buildYear;
		this.jibun = jibun;
		this.lat = lat;
		this.lng = lng;
		this.distance = distance;
	}


	/**
	 * @return the aptCode
	 */
	public int getAptCode() {
		return aptCode;
	}

	/**
	 * @param aptCode the aptCode to set
	 */
	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}

	/**
	 * @return the aptName
	 */
	public String getAptName() {
		return aptName;
	}

	/**
	 * @param aptName the aptName to set
	 */
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}

	/**
	 * @return the dongCode
	 */
	public String getDongCode() {
		return dongCode;
	}

	/**
	 * @param dongCode the dongCode to set
	 */
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	/**
	 * @return the dongName
	 */
	public String getDongName() {
		return dongName;
	}

	/**
	 * @param dongName the dongName to set
	 */
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	/**
	 * @return the buildYear
	 */
	public int getBuildYear() {
		return buildYear;
	}

	/**
	 * @param buildYear the buildYear to set
	 */
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	/**
	 * @return the jibun
	 */
	public String getJibun() {
		return jibun;
	}

	/**
	 * @param jibun the jibun to set
	 */
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 * @return the lng
	 */
	public String getLng() {
		return lng;
	}

	/**
	 * @param lng the lng to set
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}

	/**
	 * @param the distance
	 */
	
	public int getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(aptCode);
		buffer.append("\t| ");
		buffer.append(aptName);
		buffer.append("\t| ");
		buffer.append(dongCode);
		buffer.append("\t| ");
		buffer.append(dongName);
		buffer.append("\t| ");
		buffer.append(buildYear);
		buffer.append("\t| ");
		buffer.append(jibun);
		buffer.append("\t| ");
		buffer.append(lat);
		buffer.append("\t| ");
		buffer.append(lng);
		buffer.append("\t| ");
		buffer.append(distance);
		return buffer.toString();
	}
}
