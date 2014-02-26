package iwb.service.impl;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import iwb.bo.Link;
import iwb.bo.Waste;
import iwb.repository.impl.ItemDAOImpl;
import iwb.bo.Constituent;
import iwb.bo.Item;
import iwb.service.ItemService;
import restx.factory.Component;

import javax.inject.Named;


@Component @Named("itemService")
public class ItemServiceImpl implements ItemService{


    private ItemDAOImpl itemDAO;


    public ItemServiceImpl(@Named("itemDAO") ItemDAOImpl itemDAO){
        this.itemDAO = itemDAO;
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
     * Uses teh appropriate DAO method to update the item matching the oid parameter,
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
     *
     * @param item
     * @return
     */
    public Item setLinks(Item item){
        item.setLink(new Link("alternative", "/items/" + item.getId()));
        if (item.getWasteType() != null){
            item.getWasteType().setLink(new Link("alternate", "/wastes/"+item.getWasteType().getId()));
        }else{
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
        }else{
            for(Constituent constituent : item.get().getConstituents()){
                constituent.getWasteType().setLink(new Link("alternate", "/wastes/"+constituent.getWasteType().getId()));
            }
        }
        return item;
      
    }

}
