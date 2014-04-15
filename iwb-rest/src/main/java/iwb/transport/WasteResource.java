package iwb.transport;


import com.google.common.base.Optional;

import iwb.bo.TrashCustom;
import iwb.bo.Waste;
import iwb.bo.coordinates.GeoPoint2D;
import iwb.service.WasteService;
import restx.annotations.*;
import restx.factory.Component;
import restx.security.PermitAll;

import javax.inject.Named;

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
    
    @GET("/wastes/{oid}/recylcing")
    public Iterable<TrashCustom> findWasteById(String oid, Optional<String> nb){
    	Optional<GeoPoint2D> location = Optional.fromNullable(new GeoPoint2D(48.111933799999996,-1.6838946999999962));
        return wasteService.getMatchingTrashesHome(oid, nb,location);
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
