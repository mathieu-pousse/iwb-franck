package iwb.transport;

import iwb.bo.Waste;
import iwb.service.WasteService;

import javax.inject.Named;

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
public class WasteResource {

    private WasteService wasteService;

    public WasteResource(@Named("wasteService") WasteService wasteService){
        this.wasteService = wasteService;
    }

    @GET("/wastes")
    public Iterable<Waste> findWastes(Optional<String> name){
        if(name.isPresent()){
            return wasteService.getWastesByName(name.get());
        }else{
            return wasteService.getWastes();
        }
    }

    @GET("/wastes/{oid}")
    public Optional<Waste> findWasteById(String oid){
        return wasteService.getWasteById(oid);
    }
    

    @POST("/wastes")
    public Waste createWaste(Waste waste){
        return wasteService.createWaste(waste);
    }

    @PUT("/wastes/{oid}")
    public Waste updateWaste(String oid, Waste waste){
        return wasteService.updateWaste(oid, waste);
    }

    @DELETE("/wastes/{oid}")
    public void deleteWaste(String oid){
        wasteService.deleteWaste(oid);
    }

}
