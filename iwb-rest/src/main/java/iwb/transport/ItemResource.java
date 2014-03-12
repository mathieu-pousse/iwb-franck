package iwb.transport;

import com.google.common.base.Optional;

import iwb.bo.ConstituentTrash;
import iwb.bo.Item;
import iwb.bo.Constituent;
import iwb.bo.Trash;
import iwb.bo.coordinates.GeoPoint2D;
import iwb.service.impl.ItemServiceImpl;
import restx.annotations.*;
import restx.factory.Component;
import restx.security.PermitAll;

import javax.inject.Named;

@Component @RestxResource
@PermitAll
public class ItemResource {
     private ItemServiceImpl itemService;

    public ItemResource(@Named("itemService") ItemServiceImpl itemServiceImpl){
        this.itemService = itemServiceImpl;
    }

    @GET("/items")
    public Iterable<Item> findItems(Optional<String> barcode){
        if (barcode.isPresent()) {
            return itemService.getItemByBarcode(barcode.get());
        } else {
            return itemService.getItems();
        }
    }
    
    @GET("/items/{oid}")
    public Optional<Item> findItemById(String oid, Optional<String> recycling, Optional<String> nb){
    	if((recycling.isPresent() || nb.isPresent()) && nb.isPresent()){
    		Optional<GeoPoint2D> location = Optional.fromNullable(new GeoPoint2D(48.111933799999996,-1.6838946999999962));
    		return itemService.getItemAndTrash(oid,recycling,nb,location);
    	}else {
    		return itemService.getItemById(oid);
    	}
    }

    @GET("/items/{oid}/components")
    public Iterable<Constituent> findItemComponentsById(String oid){
        Optional<Item> item = itemService.getItemById(oid);
        return item.get().getConstituents();
    }

    @POST("/items")
    public Item createItem(Item item){
        return itemService.createItem(item);
    }

    @DELETE("/items/{oid}")
    public void deleteItem(String oid){
        itemService.deleteItem(oid);
    }

    @PUT("/items/{oid}")
    public Item updateItem(String oid, Item item){
        return itemService.updateItem(oid, item);
    }
    
    @GET("/items/{oid}/recycling")
    public Iterable<Trash> findTrashes(String oid, Optional<String> cityName){
    	return itemService.getTrashesByProductId(oid, cityName);
    }
    
    @GET("/items/{oid}/components/recycling")
    public Iterable<ConstituentTrash> findTrashesComponents(String oid){
    	return itemService.getConstituentTrash(oid);
    }


}
