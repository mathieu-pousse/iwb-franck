package com.zenika.bo;


public class Acronym {

	private String name;
	private String description;
	
	public Acronym(){
		
	}
	
	public Acronym(String name, String description){
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
