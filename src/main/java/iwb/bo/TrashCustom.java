package iwb.bo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Represents a simplified version of Trash with less properties
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrashCustom {

    private String id;
    private String type;
    private String color;
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
