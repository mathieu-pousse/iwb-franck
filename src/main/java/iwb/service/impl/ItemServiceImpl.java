package iwb.service.impl;

import com.google.common.base.Optional;
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

    public Item createItem(Item item) {
        return itemDAO.createItem(item);
    }

    public Optional<Item> getItemById(String oid) {
        return itemDAO.getItemById(oid);
    }

    public Iterable<Item> getItemByBarcode(String barcode) {
        return itemDAO.getItemByBarcode(barcode);
    }

    public Iterable<Item> getItems() {
        return itemDAO.getItems();
    }

    public void addConstituent(Item item, Constituent comp) {
        itemDAO.addConstituent(item, comp);
    }

    public void deleteItem(String id) {
        itemDAO.deleteItem(id);
    }

    public Item updateItem(String oid, Item item) {
        return itemDAO.updateItem(oid, item);
    }

    public void deleteConstituent(Item item, Constituent comp) {
           itemDAO.deleteConstituent(item, comp);
    }
}
