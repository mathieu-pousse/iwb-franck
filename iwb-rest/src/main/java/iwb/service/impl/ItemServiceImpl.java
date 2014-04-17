package iwb.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import iwb.bo.ConstituentTrash;
import iwb.bo.Link;
import iwb.bo.Trash;
import iwb.bo.TrashCustom;
import iwb.bo.Waste;
import iwb.repository.ItemDAO;
import iwb.repository.TrashDAO;
import iwb.repository.WasteDAO;
import iwb.repository.impl.ItemDAOImpl;
import iwb.bo.Constituent;
import iwb.bo.Item;
import iwb.bo.coordinates.GeoPoint2D;
import iwb.service.ItemService;
import iwb.service.helpers.TrashComparator;
import iwb.service.helpers.TrashHelper;
import restx.factory.Component;

import javax.inject.Named;


/**
 * @author franckylm
 *
 */
@Component @Named("itemService")
public class ItemServiceImpl implements ItemService{


    private ItemDAO itemDAO;
    private WasteDAO wasteDAO;
    private TrashDAO trashDAO;


    public ItemServiceImpl(@Named("itemDAO") ItemDAO itemDAO, @Named("wasteDAO") WasteDAO wasteDAO, @Named("trashDAO") TrashDAO trashDAO){
        this.itemDAO = itemDAO;
        this.wasteDAO = wasteDAO;
        this.trashDAO = trashDAO;
    }

    /**
     * Uses the appropriate DAO method to insert a new item inside the item collection,
     * then sets the link to the resource
     * @param item
     * @return
     */
    public Item createItem(Item item) {
        return setLinks(itemDAO.createItem(item));
    }

    /**
     * Gets the item matching the oid parameter using the appropriate DAO method,
     * then sets the sets the link to the resource
     * @param oid
     * @return
     */
    public Optional<Item> getItemById(String oid) {
        return setLinks(itemDAO.getItemById(oid));
    }

    /**
     * Gets the item matching the barcode parameter using the appropriate DAO method,
     * then sets the sets the link to the resource
     * @param barcode
     * @return
     */
    public Iterable<Item> getItemByBarcode(String barcode) {
        Iterable<Item> items = Lists.newArrayList(itemDAO.getItemByBarcode(barcode));
        for(Item item : items){
            setLinks(item);
        }
        return items;
    }
    
    /**
     * Gets the item matching the name parameter using the appropriate DAO method,
     * then sets the sets the link to the resource
     * @param barcode
     * @return
     */
    public Iterable<Item> getItemByName(String name) {
        Iterable<Item> items = Lists.newArrayList(itemDAO.getItemByName(name));
        for(Item item : items){
            setLinks(item);
        }
        return items;
    }
    
    /**
     * Gets the item matching the name or barcode parameter using the appropriate DAO method,
     * then sets the sets the link to the resource
     * @param barcode
     * @return
     */
    public Iterable<Item> getItemByBarcodeOrName(String query){
    	Iterable<Item> items = Lists.newArrayList(itemDAO.getItemByBarcodeOrName(query));
        for(Item item : items){
            setLinks(item);
        }
        return items; 
    }

    /**
     * Gets all items using the appropriate DAO method,
     * then sets the sets the link to the resource
     * @return
     */
    public Iterable<Item> getItems() {
        Iterable<Item> items = Lists.newArrayList(itemDAO.getItems());
        for(Item item : items){
            setLinks(item);
        }
        return items;
    }

    /**
     * Uses the appropriate DAO method to add a component to a product
     * @param item
     * @param comp
     */
    public void addConstituent(Item item, Constituent comp) {
        itemDAO.addConstituent(item, comp);
    }

    /**
     * Uses the appropriate DAO method to delete item matching the oid parameter
     * @param oid
     */
    public void deleteItem(String oid) {
        itemDAO.deleteItem(oid);
    }

    /**
     * Uses the appropriate DAO method to update the item matching the oid parameter,
     * then set the link to the resource
     * @param oid
     * @param item
     * @return
     */
    public Item updateItem(String oid, Item item) {
        return setLinks(itemDAO.updateItem(oid, item));
    }

    /**
     * Uses the appropriate DAO method to delete an item component
     * @param item
     * @param comp
     */
    public void deleteConstituent(Item item, Constituent comp) {
           itemDAO.deleteConstituent(item, comp);
    }
    
    /**
     * Returns the corresponding trashes for a given item
     * @param oid
     * @param cityName
     * @return
     */
    public Iterable<Trash> getTrashesByProductId(String oid, Optional<String> cityName){
    	Optional<Item> item = itemDAO.getItemById(oid);
    	if(!item.isPresent() || item.get().getConstituents() != null || item.get().getWasteType() == null){
    		return Lists.newArrayList();
    	}else{
    		String wasteId = item.get().getWasteType().getId();
    		return  trashDAO.getTrashesByWasteType(wasteDAO.getWasteById(wasteId).get().getAcronym(),5);
    	}
    }
    
	/**
	 * 
	 */
    public Iterable<ConstituentTrash> getConstituentTrash(String oid) {
    	Optional<Item> item = itemDAO.getItemById(oid);
    	Iterable<Constituent> consituents;
    	List<ConstituentTrash> list = Lists.newArrayList();
    	
    	if((consituents = item.get().getConstituents())!=null){
    		for(Constituent cons : consituents){
    			String wasteId = cons.getWasteType().getId();
    			Iterable<Trash> trashes = trashDAO.getTrashesByWasteTypeForComponents(wasteDAO.getWasteById(wasteId).get().getAcronym(),5);
    			List<Trash> tmp = Lists.newArrayList(trashes);
    			ConstituentTrash cstTrash =  new ConstituentTrash(cons, tmp.get(0));
    			list.add(cstTrash);
    		}
    	}
		return list;
	}
    
    
	public Optional<Item> getItemAndTrash(String oid, Optional<String> recycling, Optional<String> nb, Optional<GeoPoint2D> location) {
		Optional<Item> item = itemDAO.getItemById(oid);
		
		if(!recycling.isPresent())
			return item;
		int toIndex = 1;
		try{
			toIndex = Integer.parseInt(nb.get());
		}catch(NumberFormatException nfe){}
		
		if(location.isPresent()){
			if(item.get().getWasteType() != null){
				String wasteId = item.get().getWasteType().getId();
				
				List<TrashCustom> tc = TrashHelper.findMatchingTrashes(wasteDAO, trashDAO, wasteId, location.get(), toIndex);
				item.get().setTrashes(tc);
			}
			else{
				for(Constituent cons : item.get().getConstituents()){
					String wasteId = cons.getWasteType().getId();
					List<TrashCustom> tc = TrashHelper.findMatchingTrashes(wasteDAO, trashDAO, wasteId, location.get(), toIndex);
					cons.setTrashes(tc);
				}
			}
		}
		return setLinks(item);
	}
    

    /**
     *
     * @param item
     * @return
     */
    public Item setLinks(Item item){
        item.setLink(new Link("alternative", "/items/" + item.getId()));
        if (item.getWasteType() != null){
            item.getWasteType().setLink(new Link("alternate", "/wastes/"+item.getWasteType().getId()));
        }else if(item.getConstituents() != null){
            for(Constituent constituent : item.getConstituents()){
                constituent.getWasteType().setLink(new Link("alternate", "/wastes/"+constituent.getWasteType().getId()));
            }
        }
        return item;
    }

    /**
     *
     * @param item
     * @return
     */
    public Optional<Item>  setLinks(Optional<Item> item){
        item.get().setLink(new Link("alternative", "/items/" + item.get().getId()));
        if (item.get().getWasteType() != null){
            item.get().getWasteType().setLink(new Link("alternate", "/wastes/"+item.get().getWasteType().getId()));
        }else if(item.get().getConstituents() != null){
            for(Constituent constituent : item.get().getConstituents()){
                constituent.getWasteType().setLink(new Link("alternate", "/wastes/"+constituent.getWasteType().getId()));
            }
        }
        return item;
      
    }

}
