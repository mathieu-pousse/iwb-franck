package iwb.bo;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Trash {
    @Id @ObjectId
    private String id;
    private String type;
    private String description;
    private String color;
    private Iterable<String> recommendations;
    private Iterable<Waste> wastesHandled;

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

    public Iterable<String> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(Iterable<String> recommendations) {
        this.recommendations = recommendations;
    }

    public Iterable<Waste> getWastesHandled() {
        return wastesHandled;
    }

    public void setWastesHandled(Iterable<Waste> wastesHandled) {
        this.wastesHandled = wastesHandled;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
