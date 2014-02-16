package iwb.service;


import com.google.common.base.Optional;
import iwb.domain.Constituent;
import iwb.domain.Item;

import java.util.List;

public interface ItemService {

    public Item addItem(Item item);
    public Optional<Item> getItemById(String oid);
    public Optional<Item> getItemByBarcode(String barcode);
    public Iterable<Item> getAllItems();
    public void addConstituent(Item item, Constituent comp);
    public void deleteItem(String id);
    public void deleteConstituent(Item item, Constituent comp);
    public Item updateItem(String oid, Item item);

}
