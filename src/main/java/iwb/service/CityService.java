package iwb.service;


import com.google.common.base.Optional;
import iwb.bo.City;

public interface CityService {
    public City createCity(City city);
    public City updateCity(String oid, City city);
    public void deleteCity(String oid);
    public Optional<City> getCityById(String oid);
    public Iterable<City> getCities();
    public Iterable<City> getCityByName(String name);
}

