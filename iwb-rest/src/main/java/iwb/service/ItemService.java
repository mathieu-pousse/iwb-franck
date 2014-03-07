package iwb.service;


import com.google.common.base.Optional;

import iwb.bo.Constituent;
import iwb.bo.ConstituentTrash;
import iwb.bo.Item;
import iwb.bo.Trash;

public interface ItemService {

    public Item createItem(Item item);
    public Optional<Item> getItemById(String oid);
    public Iterable<Item> getItemByBarcode(String barcode);
    public Iterable<Item> getItems();
    public void addConstituent(Item item, Constituent comp);
    public void deleteItem(String id);
    public void deleteConstituent(Item item, Constituent comp);
    public Item updateItem(String oid, Item item);
    public Iterable<Trash> getTrashesByProductId(String oid, Optional<String> cityName);
    public Iterable<ConstituentTrash> getConstituentTrash(String oid);

}
