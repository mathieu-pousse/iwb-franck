package iwb.dao;


import com.google.common.base.Optional;
import iwb.domain.City;

public interface CityDAO {
    public City addCity(City city);
    public City updateCity(String oid, City city);
    public void deleteCity(String oid);
    public Optional<City> getCityById(String oid);
    public Iterable<City> getAllCities();

}
