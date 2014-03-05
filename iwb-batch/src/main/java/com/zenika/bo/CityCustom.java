package com.zenika.bo;

/**
 * Custom version of city with less properties
 * @author franckylm
 *
 */
public class CityCustom {
	
	private String code;
	private String name;
	
	public CityCustom(){}
	
	public CityCustom(String code, String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
