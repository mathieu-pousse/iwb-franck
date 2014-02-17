package iwb.rest;

import com.google.common.base.Optional;
import iwb.domain.City;
import iwb.domain.Metropolis;
import iwb.service.CityService;
import iwb.service.impl.CityServiceImpl;
import org.bson.types.ObjectId;
import restx.Status;
import restx.annotations.*;
import restx.factory.Component;
import restx.jongo.JongoCollection;
import restx.security.PermitAll;

import javax.inject.Named;

import static restx.common.MorePreconditions.checkEquals;

@Component @RestxResource
@PermitAll
public class CityResource {

    private CityServiceImpl cityService;
    private final JongoCollection cities;
    private final JongoCollection metropolises;


    public CityResource(@Named("cityService")CityServiceImpl cityService, @Named("cities") JongoCollection cities, @Named("metropolises") JongoCollection metropolises) {
        this.cities = cities;
        this.metropolises = metropolises;
        this.cityService = cityService;
    }

    @GET("/cities")
    public Iterable<City> findCities(Optional<String> name) {
        if (name.isPresent()) {
            return cities.get().find("{name: #}", name.get()).as(City.class);
        } else {
            return cities.get().find().as(City.class);
        }
    }

    @GET("/metropolises")
    public Iterable<Metropolis> findMetropolises (Optional<String> name) {
        if (name.isPresent()) {
            return metropolises.get().find("{name: #}", name.get()).as(Metropolis.class);
        } else {
            return metropolises.get().find().as(Metropolis.class);
        }
    }

    @POST("/cities")
    public City createCity(City city) {
       return cityService.addCity(city);
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
