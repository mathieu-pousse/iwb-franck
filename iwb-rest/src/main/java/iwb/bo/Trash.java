package iwb.bo;


import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongodb.BasicDBObject;

/**
 * Not only representing a bin or a trash but all recycling solutions.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Trash {
    @Id @ObjectId
    private String id;
    private String type;
    private String description;
    private String name;
    private Link link;

    /**
     * color of the trash depending on the Metropolis recycling policy
     */
    private String color;
    private Iterable<String> wastesHandled;
    private String cityCode;
    private String address;
    private Iterable<Double>  location;
    private double distanceTo;

    public Trash(){
        this.color = null;
    }
    
    public Trash(BasicDBObject obj){
    	this.id = obj.getString("_id");
    	this.type = (obj.get("description") != null) ? obj.getString("type") : this.type;
    	this.address = (obj.get("address") != null) ? obj.getString("address") : this.address;
    	this.color = (obj.get("color") != null) ? obj.getString("color") : this.color;
    	this.name = (obj.get("name") != null) ? obj.getString("name") : this.name;
    	this.cityCode = (obj.get("cityCode") != null) ? obj.getString("cityCode") : this.cityCode;
    	this.location = (Iterable<Double>) ((obj.get("location") != null) ? obj.get("location") : this.location);
    	this.wastesHandled = (Iterable<String>) ((obj.get("wastesHandled") != null) ? obj.get("wastesHandled") : this.wastesHandled);
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Iterable<String> getWastesHandled() {
		return wastesHandled;
	}

	public void setWastesHandled(Iterable<String> wastesHandled) {
		this.wastesHandled = wastesHandled;
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

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}

	public double getDistanceTo() {
		return distanceTo;
	}

	public void setDistanceTo(double distanceTo) {
		this.distanceTo = distanceTo;
	}
	
	public Iterable<Double> getLocation() {
		return location;
	}

	public void setLocation(Iterable<Double> location) {
		this.location = location;
	}

	public void toTrashCustom(){
		this.location = null;
		this.cityCode = null;
		this.wastesHandled = null;
	}
	
}
