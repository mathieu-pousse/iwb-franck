package com.zenika.bo;

import java.util.List;

public class Metropolis {
	
	private String code;
	private String label;
	private List<CityCustom> cities;
	
	public Metropolis(){
		
	}
	
	public Metropolis(String code, String label){
		this.code = code;
		this.label = label;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<CityCustom> getCities() {
		return cities;
	}
	public void setCities(List<CityCustom> cities) {
		this.cities = cities;
	}
	
	

}
