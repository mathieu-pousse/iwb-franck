package iwb.repository.impl;


import static restx.common.MorePreconditions.checkEquals;
import iwb.bo.Constituent;
import iwb.bo.Item;
import iwb.bo.Trash;
import iwb.repository.ItemDAO;

import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Named;

import org.bson.types.ObjectId;

import restx.factory.Component;
import restx.jongo.JongoCollection;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

/**
 * Item DAO implements methods to create, retreive, update and delete items
 */
@Component @Named("itemDAO")
public class ItemDAOImpl implements ItemDAO {

    private JongoCollection items;
    private JongoCollection trashes;

    public ItemDAOImpl(@Named("items") JongoCollection items, @Named("trashes") JongoCollection trashes) {
        this.items = items;
        this.trashes = trashes;
    }

    /**
     * Create a new item
     * @param item
     * @return
     */
    public Item createItem(Item item) {
        items.get().save(item);
        return item;
    }

    /**
     * Returns the Item that matches the oid parameter
     * @param oid oid of the Item to retreive
     * @return
     */
    public Optional<Item> getItemById(String oid) {
        return Optional.fromNullable(items.get().findOne(new ObjectId(oid)).as(Item.class));
    }

    /**
     * Returns the item that matches the barcode parameter
     * @param barcode
     * @return
     */
    public Iterable<Item> getItemByBarcode(String barcode) {
        return items.get().find("{barcode: #}", barcode).as(Item.class);
    }
    
    /**
     * Returns the item that matches the name parameter
     * @param barcode
     * @return
     */
    public Iterable<Item> getItemByName(String name) {
    	String nameregex = ".*"+name+".*";
    	Pattern regex = Pattern.compile(nameregex, Pattern.CASE_INSENSITIVE);
        return items.get().find("{name: #}", regex).as(Item.class);
    }
    
    /**
     * Returns the item that matches the name or barcode parameters
     * @param barcode
     * @return
     */
    public Iterable<Item> getItemByBarcodeOrName(String query){
    	String nameregex = ".*"+query+".*";
    	Pattern regex = Pattern.compile(nameregex, Pattern.CASE_INSENSITIVE);
    	return items.get().find("{$or: [{barcode: #}, {name: #}]},", query, regex).as(Item.class);
    }

    /**
     * Returns a list of all items
     * @return
     */
    public Iterable<Item> getItems() {
        return items.get().find().as(Item.class);
    }

    /**
     * Add a component to an item
     * @param item
     * @param comp
     */
    public void addConstituent(Item item, Constituent comp) {
        List<Constituent> components = Lists.newArrayList(item.getConstituents());
        if(!components.contains(comp))
            components.add(comp);
    }

    /**
     * Delete an item
     * @param id
     */
    public void deleteItem(String id) {
        items.get().remove(new ObjectId(id));
    }

    /**
     * Delete an item component
     * @param item
     * @param comp
     */
    public void deleteConstituent(Item item, Constituent comp) {
        List<Constituent> constituents = Lists.newArrayList(item.getConstituents());
        if(!constituents.contains(comp))
            constituents.remove(comp);

    }

    /**
     * Update a city and return a city updated
     * @param oid
     * @param item
     * @return
     */
    public Item updateItem(String oid, Item item) {
        checkEquals("oid", oid, "item.id", item.getId());
        items.get().save(item);
        return item;
    }
    
    public Item getItemWithHomeTrashes(String oid){
    	Item item = Optional.fromNullable(items.get().findOne(new ObjectId(oid)).as(Item.class)).get();
    	try{
    		if(item.getConstituents() == null){
    			String acronymItem = item.getWasteType().getAcronym();
    			Trash trash = trashes.get().findOne("{type: 'HOME', wastesHandled: #}", acronymItem).as(Trash.class);
        		item.setTrashes(Lists.newArrayList(trash));
    		}else{
    			for(Constituent constituent : item.getConstituents()){
    				try{
            			String acronymConstituent = constituent.getWasteType().getAcronym();
            			Trash trashConstituent = trashes.get().findOne("{type: 'HOME', wastesHandled: #}", acronymConstituent).as(Trash.class);
            			constituent.setTrashes(Lists.newArrayList(trashConstituent));
            		}catch(NullPointerException e){
            		}
    			}
    		}
    	}catch(NullPointerException e){
    		
    	}
    	
    	return item;
    }
}
