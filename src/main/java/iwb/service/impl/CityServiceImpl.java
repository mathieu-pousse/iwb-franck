package iwb.service.impl;


import com.google.common.base.Optional;
import iwb.repository.impl.CityDAOImpl;
import iwb.bo.City;
import iwb.service.CityService;
import restx.factory.Component;

import javax.inject.Named;

@Component  @Named("cityService")
public class CityServiceImpl implements CityService{

    private CityDAOImpl cityDAO;

    public CityServiceImpl(@Named("cityDAO") CityDAOImpl cityDAO ){
        this.cityDAO = cityDAO;
    }

    public City addCity(City city) {
        return cityDAO.addCity(city);
    }

    public City updateCity(String oid, City city) {
        return cityDAO.updateCity(oid, city);
    }

    public void deleteCity(String oid) {
        cityDAO.deleteCity(oid);
    }

    public Optional<City> getCityById(String oid) {
        return cityDAO.getCityById(oid);
    }
}
