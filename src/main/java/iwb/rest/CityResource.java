package iwb.rest;

import com.google.common.base.Optional;
import iwb.domain.City;
import iwb.domain.Metropolis;
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
    private final JongoCollection cities;
    private final JongoCollection metropolises;


    public CityResource(@Named("cities") JongoCollection cities, @Named("metropolises") JongoCollection metropolises) {
        this.cities = cities;
        this.metropolises = metropolises;
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
        cities.get().save(city);
        return city;
    }

    @GET("/cities/{oid}")
    public Optional<City> findCityById(String oid) {
        return Optional.fromNullable(cities.get().findOne(new ObjectId(oid)).as(City.class));
    }

    @PUT("/cities/{oid}")
    public City updateCity(String oid, City city) {
        checkEquals("oid", oid, "city.key", city.getKey());
        cities.get().save(city);
        return city;
    }

    @DELETE("/cities/{oid}")
    public Status deleteCity(String oid) {
        cities.get().remove(new ObjectId(oid));
        return Status.of("deleted");
    }

}
