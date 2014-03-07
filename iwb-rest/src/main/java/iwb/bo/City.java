package iwb.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

/**
 * Represents a City and it's characteristics
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class City {
    @Id @ObjectId
    private String id;
    private String name;
    private String ucName;
    /**
     * List of zip codes associated to a city
     */
    private Iterable<String> zipcodes;
    private Link link;
    private String code;

    public City(){
    }
    
    public City(String id, String name, String code, Iterable<String> zipcodes){
    	this.id = id;
    	this.name = name;
    	this.code = code;
    	this.zipcodes = zipcodes;
    }
    
    public City(String id, String name, String code){
    	this.id = id;
    	this.name = name;
    	this.code = code;
    }

    public String getId() {
        return id.replaceAll("(\\\\|\")", "");
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Iterable<String> getZipcodes() {
        return zipcodes;
    }

    public void setZipcodes(Iterable<String> zipcodes) {
        this.zipcodes = zipcodes;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
    

    public String getUcName() {
		return ucName;
	}

	public void setUcName(String ucName) {
		this.ucName = ucName;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
    public String toString() {
        return "City{" +
                "Id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
