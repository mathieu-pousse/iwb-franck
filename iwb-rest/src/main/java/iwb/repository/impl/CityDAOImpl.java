package iwb.repository.impl;


import com.google.common.base.Optional;
import iwb.bo.City;
import iwb.repository.CityDAO;
import org.bson.types.ObjectId;
import restx.factory.Component;
import restx.jongo.JongoCollection;

import javax.inject.Named;

import static restx.common.MorePreconditions.checkEquals;

/**
 * City DAO implements methods to create, retreive, update and delete cities
 */
@Component @Named("cityDAO")
public class CityDAOImpl implements CityDAO {

    private JongoCollection cities;

    /**
     *Gets the injected component to execute queries
     * @param cities
     */
    public CityDAOImpl(@Named("cities") JongoCollection cities){
        this.cities = cities;
    }


    /**
     * Creates a city and return return the city created
     * @param city
     * @return
     */
    public City createCity(City city) {
        cities.get().save(city);
        return city;
    }

    /**
     * Updates a city and return the updated city
     * @param oid  key value of the city to update
     * @param city represents the new version of the city
     * @return
     */
    public City updateCity(String oid, City city) {
        //First ensuring that the key values of the older and newer version of city we want to update are the same
        checkEquals("oid", oid, "city.id", city.getId());
        cities.get().save(city);
        return city;
    }

    /**
     * Deletes the city document inside the database
     * @param oid id of the city we want to delete
     */
    public void deleteCity(String oid) {
        cities.get().remove(new ObjectId(oid));
    }

    /**
     * Returns the city that matches the oid parameter value  if one exists
     * @param oid
     * @return
     */
    public Optional<City> getCityById(String oid) {
        //return Optional.fromNullable(cities.get().findOne(new ObjectId(oid)).as(City.class));
    	return Optional.fromNullable(cities.get().findOne("{_id: #}", oid).as(City.class));
    }

    /**
     * Returns all the cities
     * @return
     */
    public Iterable<City> getCities() {
        return cities.get().find().limit(10).as(City.class);
    }

    /**
     * Returns cities that matches the name parameter
     * @param name name of the city/cities we want to get
     * @return
     */
    public Iterable<City> getCityByName(String name){
        return cities.get().find("{name: #}", name).as(City.class);
    }

}
