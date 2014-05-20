package iwb.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import java.util.List;

/**
 * Represents an Item with its characteristics
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Item {

    @Id @ObjectId
    private String id;
    private String name;
    private String barcode;
    private String image;
    private Iterable<Constituent> constituents;
    private Waste wasteType;
    private Link link;
    private List<String> tags;
    private String description;
    private List<TrashCustom> trashes;


    public Item(){
        wasteType = null;
    }
    
    public Item(String id, String name, String barcode){
    	this.id = id;
    	this.name = name;
    	this.barcode = barcode;
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

    public void setConstituents(List<Constituent> component) {
        this.constituents = component;
    }

    public Waste getWasteType() {
        return wasteType;
    }

    public void setWasteType(Waste wasteType) {
        this.wasteType = wasteType;
    }

    public void setConstituents(Iterable<Constituent> constituents) {
        this.constituents = constituents;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TrashCustom> getTrashes() {
		return trashes;
	}

	public void setTrashes(List<TrashCustom> trashes) {
		this.trashes = trashes;
	}

	

	
}