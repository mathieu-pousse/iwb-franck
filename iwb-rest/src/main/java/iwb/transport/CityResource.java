package iwb.transport;

import com.google.common.base.Optional;
import iwb.AppModule;
import iwb.bo.City;
import iwb.service.impl.CityServiceImpl;
import restx.Status;
import restx.annotations.*;
import restx.factory.Component;
import restx.jongo.JongoCollection;
import restx.security.PermitAll;
import restx.security.RolesAllowed;

import javax.inject.Named;

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
