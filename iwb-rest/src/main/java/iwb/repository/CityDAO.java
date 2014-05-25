package iwb.repository;


import iwb.bo.City;

import com.google.common.base.Optional;

public interface CityDAO {
    public City createCity(City city);
    public City updateCity(String oid, City city);
    public void deleteCity(String oid);
    public Optional<City> getCityById(String oid);
    public Iterable<City> getCities();
    public Iterable<City> getCityByName(String name);
    
}
