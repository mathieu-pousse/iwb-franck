package iwb.bo;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

public class City {
    @Id @ObjectId
    private String id;
    private String name;
    private Iterable<String> zipcodes;
    private double area;
    private int population;
    private int density;
    private String label;

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

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getDensity() {
        return density;
    }

    public void setDensity(int density) {
        this.density = density;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "City{" +
                "Id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
