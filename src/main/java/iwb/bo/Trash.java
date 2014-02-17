package iwb.bo;


import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

public class Trash {
    @Id @ObjectId
    private String id;
    private String type;
    private String description;
    private Iterable<String> metropolises;
    private Iterable<String> recommendations;
    private Iterable<Waste> wasteHandled;

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

    public Iterable<String> getMetropolises() {
        return metropolises;
    }

    public void setMetropolises(Iterable<String> metropolises) {
        this.metropolises = metropolises;
    }

    public Iterable<String> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Iterable<String> recommendations) {
        this.recommendations = recommendations;
    }

    public Iterable<Waste> getWasteHandled() {
        return wasteHandled;
    }

    public void setWasteHandled(Iterable<Waste> wasteHandled) {
        this.wasteHandled = wasteHandled;
    }
}
