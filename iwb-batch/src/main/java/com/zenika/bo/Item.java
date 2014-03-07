package com.zenika.bo;

public class Item {
	
    private String name;
    private String barcode;
    private String image;
    private Iterable<Constituent> constituents;
    private Iterable<String> tags;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Iterable<Constituent> getConstituents() {
		return constituents;
	}
	public void setConstituents(Iterable<Constituent> constituents) {
		this.constituents = constituents;
	}
	public Iterable<String> getTags() {
		return tags;
	}
	public void setTags(Iterable<String> tags) {
		this.tags = tags;
	}
    
    

}
