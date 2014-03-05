package iwb.bo;

import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * Represents item's components
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Constituent {

    private String name;
    private Waste wasteType;
    private Link link;

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
}
