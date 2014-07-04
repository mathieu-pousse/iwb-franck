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
import com.google.common.primitives.Ints;

@Component @RestxResource
@PermitAll
public class TrashResource {

    private TrashService trashService;

    public TrashResource(@Named("trashService") TrashService trashService){
        this.trashService = trashService;
    }

    @GET("/trashes")
    public Iterable<Trash> findTrashes(Optional<String> type, Optional<String> acr, 
    		Optional<String> number, Optional<String> page){
        if(type.isPresent()){
            return trashService.getTrashesByWasteType(type.get(),5);
        }else if(acr.isPresent()){
        	return Lists.newArrayList(trashService.getTrashHome(acr.get()));
        }else if(number.isPresent() && page.isPresent()){
        	final int NUMBER_OF_ITEMS = Integer.parseInt(number.get());
        	final int PAGE_NUMBER = Integer.parseInt(page.get());
        	return trashService.getTrashesPagination(NUMBER_OF_ITEMS, PAGE_NUMBER);
        }
        else{
            return trashService.getTrashes();
        }
    }
    
    @GET("/trashes/count")
    public Integer findTrashes(){ 
    	return new Integer(Ints.checkedCast(trashService.getTrashNumber()));
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