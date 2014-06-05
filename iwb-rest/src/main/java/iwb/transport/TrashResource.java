package iwb.transport;

import iwb.bo.Trash;
import iwb.service.TrashService;

import javax.inject.Named;

import org.assertj.core.util.Lists;

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
    public Iterable<Trash> findTrashes(Optional<String> type, Optional<String> acr){
        if(type.isPresent()){
            return trashService.getTrashesByWasteType(type.get(),5);
        }else if(acr.isPresent()){
        	return Lists.newArrayList(trashService.getTrashHome(acr.get()));
        }
        else{
            return trashService.getTrashes();
        }
    }
    
    
    @GET("/trash")
    public Trash findTrashHome(Optional<String> acr){
        if(acr.isPresent()){
        	return trashService.getTrashHome(acr.get());
        }
        else{
            return new Trash();
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