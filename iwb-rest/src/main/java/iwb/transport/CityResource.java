package iwb.transport;

import iwb.bo.City;
import iwb.service.impl.CityServiceImpl;

import javax.inject.Named;

import restx.Status;
import restx.annotations.DELETE;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.PUT;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

import com.google.common.base.Optional;

@Component @RestxResource
@PermitAll
public class CityResource {

    private CityServiceImpl cityService;

    public CityResource(@Named("cityService")CityServiceImpl cityService) {
        this.cityService = cityService;
    }

    @GET("/cities")
    public Iterable<City> findCities(Optional<String> name) {
        if (name.isPresent()) {
            return cityService.getCityByName(name.get());
        } else {
            return cityService.getCities();
        }
    }

    @POST("/cities")
    public City createCity(City city) {
       return cityService.createCity(city);
    }

    @GET("/cities/{oid}")
    public Optional<City> findCityById(String oid) {
        return cityService.getCityById(oid);
    }

    @PUT("/cities/{oid}")
    public City updateCity(String oid, City city) {
        return cityService.updateCity(oid, city);
    }

    @DELETE("/cities/{oid}")
    public Status deleteCity(String oid) {
        cityService.deleteCity(oid);
        return Status.of("deleted");
    }

}
