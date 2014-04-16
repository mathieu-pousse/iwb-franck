package iwb.bo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;



/**
 * Represents item's components
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Constituent {

    private String name;
    private String image;
    private Waste wasteType;
    private List<TrashCustom> trashes;
    private Link link;
    
    public Constituent(){}
    
    public Constituent(String name, Waste waste, List<TrashCustom> trashes){
    	this.name = name;
    	this.wasteType = waste;
    	this.trashes = trashes;
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
	
	public List<TrashCustom> getTrashes() {
		return trashes;
	}

	public void setTrashes(List<TrashCustom> trashes) {
		this.trashes = trashes;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
    
    
}
