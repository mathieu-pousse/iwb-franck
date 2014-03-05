package iwb.service.impl;


import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import iwb.bo.Link;
import iwb.repository.impl.CityDAOImpl;
import iwb.bo.City;
import iwb.service.CityService;
import restx.factory.Component;

import javax.inject.Named;
import java.util.List;

@Component  @Named("cityService")
public class CityServiceImpl implements CityService{

    private CityDAOImpl cityDAO;

    public CityServiceImpl(@Named("cityDAO") CityDAOImpl cityDAO ){
        this.cityDAO = cityDAO;
    }

    /**
     * Uses the appropriate DAO method to create a new city,
     * then sets the link to the resource
     * @param city
     * @return
     */
    public City createCity(City city) {
        return setLinks(cityDAO.createCity(city));
    }

    /**
     * Uses the appropriate DAO method to update the city matching the oid parameter,
     * then set the link to the resource before returning it.
     * @param oid
     * @param city
     * @return
     */
    public City updateCity(String oid, City city) {
        return setLinks(cityDAO.updateCity(oid, city));
    }

    /**
     * Uses the appropriate DAO method to Delete the city matching the oid parameter
     * @param oid
     */
    public void deleteCity(String oid) {
        cityDAO.deleteCity(oid);
    }

    /**
     * Gets the city matching the oid parameter using the appropriate DAO method,
     * then sets the link to the resource and return the city object
     * @param oid
     * @return
     */
    public Optional<City> getCityById(String oid) {
        return setLinks(cityDAO.getCityById(oid));
    }

    /**
     * Gets the cities using the appropriate DAO method,
     * then sets the link to the resource and return the city object
     * @return
     */
    public Iterable<City> getCities(){
        Iterable<City> cities = Lists.newArrayList(cityDAO.getCities());
        for(City city : cities){
            setLinks(city);
        }
        return cities;
    }

    /**
     * Gets the cities matching the name parameter using the appropriate DAO method,
     * then sets the link to the resource and return the city object
     * @param name
     * @return
     */
    public Iterable<City> getCityByName(String name){
        Iterable<City> cities = Lists.newArrayList(cityDAO.getCityByName(name));
        for(City city : cities){
            setLinks(city);
        }
        return cities;
    }

    /**
     *
     * @param city
     * @return
     */
    public City setLinks(City city){
        city.setLink(new Link("alternate", "/cities/"+city.getId()));
        return city;
    }

    /**
     *
     * @param city
     * @return
     */
    public Optional<City> setLinks(Optional<City> city){
        city.get().setLink(new Link("alternate", "/cities/"+city.get().getId()));
        return city;
    }



}
