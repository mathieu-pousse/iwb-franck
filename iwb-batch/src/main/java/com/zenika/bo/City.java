package com.zenika.bo;

import org.springframework.data.annotation.Id;


public class City {
	
	private String name;
	private String ucName;
	private String zipcode;
	/**
	 * Code commune
	 */
	@Id
	private String code;
	private String longitude;
	private String latitude;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUcName() {
		return ucName;
	}
	public void setUcName(String ucName) {
		this.ucName = ucName;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	
	
	
}
