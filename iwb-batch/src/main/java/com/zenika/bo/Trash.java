package com.zenika.bo;


public class Trash {
	
	/**
	 * Identifiant du point d'apport volontaire
	 */
	private String pavID;
	
	/**
	 * Trash type BIN/GARBAGE/HOME
	 */
    private String type;
    
    /**
     * Color for home trashes
     */
    private String color;
    private String name;
    private String cityCode;
    private String address;
    private Iterable<String> wastesHandled;
    private Iterable<Double>  location;
    
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Iterable<String> getWastesHandled() {
		return wastesHandled;
	}
	public void setWastesHandled(Iterable<String> wastesHandled) {
		this.wastesHandled = wastesHandled;
	}
	public String getPavID() {
		return pavID;
	}
	public void setPavID(String pavID) {
		this.pavID = pavID;
	}
	public Iterable<Double> getLocation() {
		return location;
	}
	public void setLocation(Iterable<Double> location) {
		this.location = location;
	}	
	
	
}
