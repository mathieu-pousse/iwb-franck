package iwb.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import java.util.List;

/**
 * Represents a group of cities where recycling policies are the same
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Metropolis {

    @Id @ObjectId
    private String id;
    private String name;
    private String region;
    private String department;
    private Iterable<TrashCustom> trashes;
    private Iterable<CityCustom> cities;
    private String description;
    private Link link;

    public Metropolis(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Iterable<TrashCustom> getTrashes() {
        return trashes;
    }

    public void setTrashes(Iterable<TrashCustom> trashes) {
        this.trashes = trashes;
    }

    public Iterable<CityCustom> getCities() {
        return cities;
    }

    public void setCities(Iterable<CityCustom> cities) {
        this.cities = cities;
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

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }
}
