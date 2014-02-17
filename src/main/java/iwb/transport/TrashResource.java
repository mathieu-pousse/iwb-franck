package iwb.transport;

import com.google.common.base.Optional;
import iwb.bo.Trash;
import iwb.service.TrashService;
import restx.annotations.*;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

import javax.inject.Named;

@Component @RestxResource
@PermitAll
public class TrashResource {

    private TrashService trashService;

    public TrashResource(@Named("trashService") TrashService trashService){
        this.trashService = trashService;

    }

    @GET("/trashes")
    public Iterable<Trash> findTrashes(Optional<String> zip, Optional<String> waste){
        if(zip.isPresent() || waste.isPresent()){
            return null;
        }else{
            return trashService.getTrashes();
        }
    }

    @POST("/trashes")
    public Trash createTrash(Trash trash){
        return trashService.createTrash(trash);
    }

    @GET("/trashes/{oid}")
    public Optional<Trash> findTrashById(String oid){
        return trashService.getTrashById(oid);
    }

    @DELETE("trashes/{oid}")
    public void deleteTrash(String oid){
        trashService.deleteTrash(oid);
    }


}