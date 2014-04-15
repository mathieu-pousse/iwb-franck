package iwb.bo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents a simplified version of Trash with less properties
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrashCustom {

    private String id;
    private String type;
    private String color;
    private String name;
    private String address;
    private double distanceTo;
    private String longitude;
    private String latitude;
    private Link link;
    
    public TrashCustom(){}
    
    public TrashCustom(Trash trash){
    	this.id = trash.getId();
    	this.type = trash.getType();
    	this.color = trash.getColor();
    	this.name = trash.getName();
    	this.address = trash.getAddress();
    	this.distanceTo = trash.getDistanceTo();
    	if(trash.getLatitude() !=null && trash.getLongitude() != null){
    		setCoords(trash.getLatitude(), trash.getLongitude());
    	}
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getDistanceTo() {
		return distanceTo;
	}

	public void setDistanceTo(double distanceTo) {
		this.distanceTo = distanceTo;
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
	
	public void setCoords(String latitude, String longitude){
		this.latitude = latitude;
		this.longitude = longitude;
	}
    
    
}
