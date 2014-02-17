package iwb.service;


import com.google.common.base.Optional;
import iwb.domain.City;

public interface CityService {
    public City addCity(City city);
    public City updateCity(String oid, City city);
    public void deleteCity(String oid);
    public Optional<City> getCityById(String oid);
}

