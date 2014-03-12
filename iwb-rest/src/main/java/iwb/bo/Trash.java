package iwb.bo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

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
    private String longitude;
    private String latitude;
    private double distanceTo;

    public Trash(){
        this.color = null;
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

	public double getDistanceTo() {
		return distanceTo;
	}

	public void setDistanceTo(double distanceTo) {
		this.distanceTo = distanceTo;
	}
	
	public void toTrashCustom(){
		this.latitude = null;
		this.longitude = null;
		this.cityCode = null;
		this.wastesHandled = null;
	}
	
}
