package iwb.domain;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

public class Constituent {

    private String name;
    private Waste wasteType;


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
}
