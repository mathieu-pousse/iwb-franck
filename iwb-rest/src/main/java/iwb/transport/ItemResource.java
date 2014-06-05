package iwb.transport;

import iwb.bo.Constituent;
import iwb.bo.Item;
import iwb.service.impl.ItemServiceImpl;

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
public class ItemResource {
	
     private ItemServiceImpl itemService;

    public ItemResource(@Named("itemService") ItemServiceImpl itemServiceImpl){
        this.itemService = itemServiceImpl;
    }

    @GET("/items")
    public Iterable<Item> findItems(Optional<String> query){
        if (query.isPresent()) {
            return itemService.getItemByBarcodeOrName(query.or(""));
        }else{
        	return itemService.getItems();
        }
    }
    
    @GET("/item/{oid}")
    public Optional<Item> findItems(String oid){
    	return Optional.fromNullable(itemService.getItemWithHomeTrashes(oid));
    }
    
    @GET("/items/{oid}")
    public Optional<Item> findItemById(String oid, Optional<String> recycling, Optional<String> nb){
    	return itemService.getItemById(oid);
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
 
    
}
