package com.zenika.bo;

import java.util.List;

public class Metropolis {
	
	private String code;
	private String name;
	private List<CityCustom> cities;
	
	public Metropolis(){}
	
	public Metropolis(String code, String name){
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

	public List<CityCustom> getCities() {
		return cities;
	}
	public void setCities(List<CityCustom> cities) {
		this.cities = cities;
	}
	
	

}
