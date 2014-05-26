package iwb.service.impl;

import iwb.bo.Constituent;
import iwb.bo.Item;
import iwb.bo.Trash;
import iwb.repository.ItemDAO;
import iwb.service.ItemService;
import iwb.service.helpers.ImageUrlHelper;
import iwb.service.helpers.LinkHelper;

import javax.inject.Named;

import restx.factory.Component;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;


/**
 * @author franckylm
 *
 */
@Component @Named("itemService")
public class ItemServiceImpl implements ItemService{


    private ItemDAO itemDAO;
    public LinkHelper linkHelper;
    public ImageUrlHelper imageHelper;


    public ItemServiceImpl(@Named("itemDAO") ItemDAO itemDAO, @Named("imageHelper") ImageUrlHelper imageHelper){
        this.itemDAO = itemDAO;
        this.linkHelper = new LinkHelper();
        this.imageHelper = imageHelper; 
    }

    /**
     * Uses the appropriate DAO method to insert a new item inside the item collection,
     * then sets the link to the resource
     * @param item
     * @return
     */
    public Item createItem(Item item) {
    	removeLinksAndImages(item);
    	Item result = itemDAO.createItem(item);
        return this.addLinksAndImages(result);
    }

    /**
     * Gets the item matching the oid parameter using the appropriate DAO method,
     * then sets the sets the link to the resource
     * @param oid
     * @return
     */
    public Optional<Item> getItemById(String oid) {
    	Optional<Item> result = itemDAO.getItemById(oid);
        return addLinksAndImages(result);
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
            addLinksAndImages(item);
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
            addLinksAndImages(item);
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
            addLinksAndImages(item);
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
            addLinksAndImages(item);
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
    	return null;
    }
     

    /**
     *
     * @param item
     * @return
     */
    public Item setLinks(Item item){
    	linkHelper.createLink(item);
    	linkHelper.createLink(item.getWasteType());
    	if(item.getConstituents() == null){
    		return item;
    	}
    	for(Constituent constituent : item.getConstituents()){
    		linkHelper.createLink(constituent.getWasteType());
    	}
        return item;
    }
    
    public Item unSetLinks(Item item){
    	linkHelper.removeLink(item);
    	linkHelper.removeLink(item.getWasteType());
    	if(item.getConstituents() == null){
    		return item;
    	}
    	for(Constituent constituent : item.getConstituents()){
    		linkHelper.removeLink(constituent.getWasteType());
    	}
        return item;
    }

    /**
     *
     * @param item
     * @return
     */
    public Optional<Item>  setLinks(Optional<Item> item){
    	setLinks(item.get());
        return item;
    }
    
    public Optional<Item>  unSetLinks(Optional<Item> item){
    	unSetLinks(item.get());
        return item;
    } 
    
    public Item setImages(Item item){
    	item.setImage(imageHelper.addBasePathUrl(item.getImage()));
    	if(item.getConstituents() == null){
    		return item;
    	}
    	for(Constituent constituent : item.getConstituents()){
    		constituent.setImage(imageHelper.addBasePathUrl(constituent.getImage()));
    	}
    	return item;
    }
    
    public Optional<Item> setImages(Optional<Item> item){
    	setImages(item.get());
    	return item;
    }
    
    
    public Item unSetImages(Item item){
    	item.setImage(imageHelper.removeBasePathUrl(item.getImage()));
    	if(item.getConstituents() == null){
    		return item;
    	}
    	for(Constituent constituent : item.getConstituents()){
    		constituent.setImage(imageHelper.removeBasePathUrl(constituent.getImage()));
    	}
    	return item;
    }
    
    public Optional<Item> unSetImages(Optional<Item> item){
    	unSetImages(item.get());
    	return item;
    }
    
    public Item removeLinksAndImages(Item item){
    	unSetImages(item);
    	return unSetLinks(item);
    }
    
    public Optional<Item> removeLinksAndImages(Optional<Item> item){
    	removeLinksAndImages(item.get());
    	return item;
    }
    
    public Item addLinksAndImages(Item item){
    	setImages(item);
    	return setLinks(item);
    }
    
    public Optional<Item> addLinksAndImages(Optional<Item> item){
    	addLinksAndImages(item.get());
    	return item;
    }
    

}
