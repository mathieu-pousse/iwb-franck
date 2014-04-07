package iwb.bo;

import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * Represents item's components
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Constituent {

    private String name;
    private String image;
    private Waste wasteType;
    private TrashCustom trash;
    private Link link;
    
    public Constituent(){}
    
    public Constituent(String name, Waste waste, TrashCustom trash){
    	this.name = name;
    	this.wasteType = waste;
    	this.trash = trash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Waste getWasteType() {
        return wasteType;
    }

    public void setWasteType(Waste wasteType) {
        this.wasteType = wasteType;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

	public TrashCustom getTrash() {
		return trash;
	}

	public void setTrash(TrashCustom trash) {
		this.trash = trash;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
    
    
}
