package iwb.rest;

import com.google.common.base.Optional;
import iwb.domain.Item;
import iwb.domain.Constituent;
import iwb.domain.Metropolis;
import iwb.service.impl.ItemServiceImpl;
import org.bson.types.ObjectId;
import restx.Status;
import restx.annotations.*;
import restx.factory.Component;
import restx.factory.Factory;
import restx.security.PermitAll;

import javax.inject.Named;

import static restx.common.MorePreconditions.checkEquals;

@Component @RestxResource
@PermitAll
public class ItemResource {
     private ItemServiceImpl itemService;

    public ItemResource(@Named("itemService") ItemServiceImpl itemServiceImpl){
        this.itemService = itemServiceImpl;
    }

    @GET("/items")
    public Iterable<Item> findAllItems(){
        return itemService.getAllItems();
    }

    @GET("/items/{oid}")
    public Optional<Item> findItemById(String oid){
        return itemService.getItemById(oid);
    }

    @GET("/items/{oid}/components")
    public Iterable<Constituent> findItemComponentsById(String oid){
        Optional<Item> item = itemService.getItemById(oid);
        return item.get().getConstituents();
    }

    @POST("/items")
    public Item createItem(Item item){
        return itemService.addItem(item);
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
