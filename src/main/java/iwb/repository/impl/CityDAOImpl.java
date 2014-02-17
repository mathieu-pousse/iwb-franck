package iwb.repository.impl;


import com.google.common.base.Optional;
import iwb.bo.City;
import iwb.repository.CityDAO;
import org.bson.types.ObjectId;
import restx.factory.Component;
import restx.jongo.JongoCollection;

import javax.inject.Named;

import static restx.common.MorePreconditions.checkEquals;

@Component @Named("cityDAO")
public class CityDAOImpl implements CityDAO {

    private JongoCollection cities;

    public CityDAOImpl(@Named("cities") JongoCollection cities){
        this.cities = cities;
    }

    public City createCity(City city) {
        cities.get().save(city);
        return city;
    }

    public City updateCity(String oid, City city) {
        checkEquals("oid", oid, "city.id", city.getId());
        cities.get().save(city);
        return city;
    }

    public void deleteCity(String oid) {
        cities.get().remove(new ObjectId(oid));
    }

    public Optional<City> getCityById(String oid) {
        return Optional.fromNullable(cities.get().findOne(new ObjectId(oid)).as(City.class));
    }

    public Iterable<City> getCities() {
        return cities.get().find().as(City.class);
    }

    public Iterable<City> getCityByName(String name){
        return cities.get().find("{name: #}", name).as(City.class);
    }

}
