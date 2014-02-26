package iwb.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

/**
 * Represents a City and it's characteristics
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class City {
    @Id @ObjectId
    private String id;
    private String name;

    /**
     * List of zip codes associated to a city
     */
    private Iterable<String> zipcodes;
    private String description;
    private Link link;

    public City(){
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

    public Iterable<String> getZipcodes() {
        return zipcodes;
    }

    public void setZipcodes(Iterable<String> zipcodes) {
        this.zipcodes = zipcodes;
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

    @Override
    public String toString() {
        return "City{" +
                "Id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
