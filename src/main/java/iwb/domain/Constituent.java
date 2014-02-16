package iwb.domain;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

public class Constituent {

    @Id @ObjectId
    private String id;
    private String name;
    private Waste wasteType;

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

    public Waste getWasteType() {
        return wasteType;
    }

    public void setWasteType(Waste wasteType) {
        this.wasteType = wasteType;
    }
}
