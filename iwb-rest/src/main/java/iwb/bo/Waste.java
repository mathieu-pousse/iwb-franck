package iwb.bo;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents a waste type
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Waste {

    @Id @ObjectId
    private String id;
    private String name;
    private String description;
    private String acronym;
    private Link link;
    
    public Waste(){}
    
    public Waste(String id, String name, String acronym){
    	this.id = id;
    	this.name = name;
    	this.acronym = acronym;
    }

    public String getId() {
        return id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
    
    
}
