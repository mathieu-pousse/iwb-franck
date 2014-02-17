package iwb.transport;


import com.google.common.base.Optional;
import iwb.bo.Metropolis;
import iwb.service.MetropolisService;
import restx.annotations.*;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

import javax.inject.Named;

@Component @RestxResource
@PermitAll
public class MetropolisResource {

    private MetropolisService metropolisService;

    public MetropolisResource(@Named("metropolisService") MetropolisService metropolisService){
        this.metropolisService = metropolisService;
    }

    @GET("/metropolises")
    public Iterable<Metropolis> findMetropolises (Optional<String> name) {
        return metropolisService.getMetropolises();
    }

    @POST("/metropolises")
    public Metropolis createMetropolis(Metropolis metropolis){
        return metropolisService.createMetropolis(metropolis);
    }

    @DELETE("/metropolises/{oid}")
    public void deleteMetropolis(String oid){
        metropolisService.deleteMetropolis(oid);
    }

    @GET("/metropolises/{oid}")
    public Optional<Metropolis> findMetropolisById(String oid){
        return metropolisService.getMetropolisById(oid);
    }

    @PUT("/metropolises/{oid}")
    public Metropolis updateMetropolis(String oid, Metropolis metropolis){
        return updateMetropolis(oid, metropolis);
    }

}