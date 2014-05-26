package iwb.transport;

import iwb.bo.Trash;
import iwb.service.TrashService;

import javax.inject.Named;

import restx.annotations.DELETE;
import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

import com.google.common.base.Optional;

@Component @RestxResource
@PermitAll
public class TrashResource {

    private TrashService trashService;

    public TrashResource(@Named("trashService") TrashService trashService){
        this.trashService = trashService;

    }

    @GET("/trashes")
    public Iterable<Trash> findTrashes(Optional<String> type){
        if(type.isPresent()){
            return trashService.getTrashesByWasteType(type.get(),5);
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

    @DELETE("/trashes/{oid}")
    public void deleteTrash(String oid){
        trashService.deleteTrash(oid);
    }

}